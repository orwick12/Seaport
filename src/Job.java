
import javax.print.Doc;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class Job extends Thing implements Runnable{

        static Random rn = new Random();
        JPanel jPPanel;
        Ship ship;               // ND
        //     Person worker = null; // ND
        int jobIndex;
        double jobTime;
        String jobName = "";
        JProgressBar pm = new JProgressBar();
        boolean goFlag = true, noKillFlag = true;
        JButton jbGo = new JButton("Stop");
        JButton jbKill = new JButton("Cancel");
        Status status = Status.SUSPENDED;
        Seaport port;
        //Thread thread;
        boolean isFinished = false;

        enum Status {RUNNING, SUSPENDED, WAITING, DONE};

        private double duration;
        private ArrayList<String> skills; //skills of a person

        public Job (HashMap <Integer, Ship> shipHM, Scanner sc) {
            //super(sc);
            jPPanel = new JPanel();
            //sc.next (); // dump first field, j
            jobName = sc.next ();
            jobIndex = sc.nextInt ();
            parent = sc.nextInt ();
            ship = shipHM.get(parent); // ND
            //worker = (Person) (ship.get()); // ND
            jobTime = sc.nextDouble();
            pm = new JProgressBar ();
            pm.setStringPainted (true);
            jPPanel.add (pm);
            jPPanel.add (new JLabel (ship.name, SwingConstants.CENTER)); // ND
            jPPanel.add (new JLabel (jobName, SwingConstants.CENTER));

            jPPanel.add (jbGo);
            jPPanel.add (jbKill);

            jbGo.addActionListener (new ActionListener() {
                public void actionPerformed (ActionEvent e) {
                    toggleGoFlag ();
                }
            });

            jbKill.addActionListener (new ActionListener () {
                public void actionPerformed (ActionEvent e) {
                    setKillFlag ();
                }
            });

            new Thread (this).start();

        }


//     JLabel jln = new JLabel (worker.name);
//       following shows how to align text relative to icon
//     jln.setHorizontalTextPosition (SwingConstants.CENTER);
//     jln.setHorizontalAlignment (SwingConstants.CENTER);
//     parent.jrun.add (jln);

        public void toggleGoFlag () {
            goFlag = !goFlag;
        } // end method toggleRunFlag

        public void setKillFlag () {
            noKillFlag = false;
            jbKill.setBackground (Color.red);
        } // end setKillFlag

        void showStatus (Status st) {
            status = st;
            switch (status) {
                case RUNNING:
                    jbGo.setBackground (Color.green);
                    jbGo.setText ("Running");
                    break;
                case SUSPENDED:
                    jbGo.setBackground (Color.yellow);
                    jbGo.setText ("Suspended");
                    break;
                case WAITING:
                    jbGo.setBackground (Color.orange);
                    jbGo.setText ("Waiting turn");
                    break;
                case DONE:
                    jbGo.setBackground (Color.red);
                    jbGo.setText ("Done");
                    break;
            } // end switch on status
        } // end showStatus

    public void run () {

        long time = System.currentTimeMillis();
        long startTime = time;
        double stopTime = time + 1000 * jobTime;
        double duration = stopTime - time;
        Dock md = ship.dock;
        Ship newShip = ship.port.queue.get(0);
        System.out.println(newShip + "-------------");
        // ND: sync on job's ship's port, since some ships are in a queue, not a dock
        synchronized (ship.port) {
            while (ship.busyFlag || ship.dock == null) {  // ND: it's the ship that is busy
                showStatus(Status.WAITING);
                try {
                    ship.port.wait();
                    System.out.println(ship.jobs + " waiting");
                } catch (InterruptedException e) {
                } // end try/catch block
            } // end while
            ship.busyFlag = true;

         // end sychronized

        System.out.printf(">>>>> Ship: %-20s, Port: %-20s, Dock: %-10s, Job: %-15s, Duration: %10.2f\n",
                ship.name,
                ship.port.name,
                (md == null) ? "--" : md.name,
                jobName,
                duration
        );

        while (time < stopTime && noKillFlag) { // while time is zero for a job start another
            try {
                Thread.sleep(100);

                if (goFlag) {
                    showStatus(Status.RUNNING);
                    time += 1000;
                    // System.out.println(ship.busyFlag + " busy ");
                    pm.setValue((int) (((time - startTime) / duration) * 100));
                } else {
                    showStatus(Status.SUSPENDED);
                } // end if stepping
            } catch (InterruptedException e) {
            }
            if (pm.getValue() == 100) {
                showStatus(Status.DONE);
                changeDock(newShip, ship);
                setKillFlag();
                ship.port.notifyAll();
            }

        }
        } // end running
    }
    // move a ship from a dock to a que when the number of jobs for a ship becomes 0


        public String toString () {
            String sr = String.format ("j:%7d:%15s:%7d:%5f", jobIndex, jobName, ship.index, jobTime);
            return sr;
        } //end method toString


        public void setDuration(int r){
            if (r > 0){
                duration = r;
            }
        }
        public double getDuration(){
            return duration;
        }

    public Seaport getPort(){
            return this.port;
    }
    public void setPort(){
            this.port = port;
    }

   //public void runJob(){
    //        this.thread.start();

   // }

    //
    public void changeDock(Ship newShip, Ship oldShip){
            oldShip.dock.setShip(newShip);
            newShip.dock = oldShip.dock;
            oldShip.dock=null;
    }

}
