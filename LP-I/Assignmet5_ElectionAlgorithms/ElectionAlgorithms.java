import java.util.*;

public class ElectionAlgorithms {
    static int token[] = new int[100];
    static int l = 0;
    static int n;
    static int pro[] = new int[100];
    static int sta[] = new int[100];
    static int co;

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\nMenu:");
            System.out.println("1. Ring Election Algorithm");
            System.out.println("2. Bully Election Algorithm");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    ringElection(sc);
                    break;
                case 2:
                    bullyElection(sc);
                    break;
                case 3:
                    System.out.println("!!THANK YOU!!");
                    break;
                default:
                    System.out.println("Invalid choice. Please select a valid option.");
            }
        } while (choice != 3);

        sc.close();
    }

    public static void print() {
        System.out.print("\nToken received: ");
        for (int i = 0; i < l; i++)
            System.out.print(token[i] + ", ");
        System.out.println();
    }

    public static void ringElection(Scanner sc) {
        int winner, max = -1;

        System.out.print("\nEnter the Total Number of processes: ");
        n = sc.nextInt();
        winner = n;

        int processes[] = new int[n + 1];
        int status[] = new int[n + 1];

        System.out.println("\nEnter the status of processes (1/0) .....");
        for (int i = 0; i < n; i++) {
            processes[i] = i;
            System.out.print("\nEnter the status of process " + i + ": ");
            status[i] = sc.nextInt();
        }

        System.out.print("\nEnter the process initiator: ");
        int x = sc.nextInt();

        int i = x;
        while (i < n) {
            if (status[i] == 1) {
                int next = i + 1;
                while (next < n) {
                    if (status[next] == 1) {
                        System.out.println("\nElection message is sent from " + i + " to " + next);
                        token[l] = i;
                        l++;
                        print();
                        winner = next;
                        break;
                    } else {
                        next++;
                    }
                }
            }
            i++;
        }

        System.out.println("\nElection message is sent from " + winner + " to 0");
        token[l] = winner;
        l++;
        print();
        i = 0;
        while (i < x) {
            if (status[i] == 1) {
                int next = i + 1;
                while (next < n) {
                    if (status[next] == 1) {
                        System.out.println("\nElection message is sent from " + i + " to " + next);
                        token[l] = i;
                        l++;
                        print();
                        break;
                    } else {
                        next++;
                    }
                }
            }
            i++;
        }

        for (int j = 0; j < l; j++) {
            if (token[j] > max)
                max = token[j];
        }
        System.out.println("\nCoordinator is " + max);
    }

    public static void bullyElection(Scanner sc) {
        System.out.print("\nEnter Total number of processes: ");
        n = sc.nextInt();

        for (int i = 0; i < n; i++) {
            System.out.print("\nFor process " + (i + 1) + ":");
            System.out.print("\n\t Status (0/1) : ");
            sta[i] = sc.nextInt();
            System.out.print("\n\t Priority : ");
            pro[i] = sc.nextInt();
        }

        System.out.print("\nWhich process will initiate election? : ");
        int ele = sc.nextInt();

        elect(ele);
        System.out.println("\nFinal coordinator is " + co);
    }

    static void elect(int ele) {
        ele--;
        co = ele + 1;
        for (int i = 0; i < n; i++) {
            if (pro[ele] < pro[i]) {
                System.out.println("\nElection message is sent from " + (ele + 1) + " to " + (i + 1));
                if (sta[i] == 1)
                    elect(i + 1);
            }
        }
    }
}
