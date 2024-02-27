import java.util.*;

public class MemoryPlacement {
    public static void main(String args[]) {
        int blocks[], processes[], nh, np;
        Scanner sc = new Scanner(System.in);
        boolean flag = true;
        System.out.println("\n\n\t\t\t === MEMORY PLACEMENT STRATEGIES ===");
        System.out.print("\n\n\t\t Enter the number of Holes : ");
        nh = sc.nextInt();
        System.out.print("\n\t\t Enter the number of Processes : ");
        np = sc.nextInt();
        blocks = new int[nh];
        processes = new int[np];
        System.out.println("\n\t\t === Enter the sizes of blocks ... ");
        for (int i = 0; i < nh; i++) {
            System.out.print("\n\t\t\t Enter the size of block no. " + (i + 1) + " = ");
            blocks[i] = sc.nextInt();
        }
        System.out.println("\n\t\t === Enter the sizes of Processes ... ");
        for (int i = 0; i < np; i++) {
            System.out.print("\n\t\t\t Enter the size of Process no. " + (i + 1) + " = ");
            processes[i] = sc.nextInt();
        }
        while (flag) {
            System.out.println("\n ==== Main-Menu ====\n\t 1. First Fit\n\t 2. Next Fit"
                    + "\n\t 3. Best Fit\n\t 4. Worst Fit\n\t 5. EXIT...");

            System.out.print("\n\t Enter choice : ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("\n\t\t\t\t 1.] First Fit ");
                    FirstFit obj1 = new FirstFit();
                    obj1.firstFit(blocks, processes, nh, np);
                    System.out.println("\n\t\t\t ==========================================");
                    break;
                case 2:
                    System.out.println("\n\t\t\t\t 2.] Next Fit ");
                    NextFit obj2 = new NextFit();
                    obj2.nextFit(blocks, processes, nh, np);
                    System.out.println("\n\t\t\t ==========================================");
                    break;
                case 3:
                    System.out.println("\n\t\t\t\t 3.] Best Fit ");
                    BestFit obj3 = new BestFit();
                    obj3.bestFit(blocks, processes, nh, np);
                    System.out.println("\n\t\t\t ==========================================");
                    break;
                case 4:
                    System.out.println("\n\t\t\t\t 4.] Worst Fit ");
                    WorstFit obj4 = new WorstFit();
                    obj4.worstFit(blocks, processes, nh, np);
                    System.out.println("\n\t\t\t ==========================================");
                    break;
                case 5:
                    flag = false;
                    System.out.print("\n\t\t\t\t Thank You ...!!");
                    System.out.println("\n\n\t\t\t\t *****=====*****");
                    break;
                default:
                    System.out.print("\n\t\t Invalid Choice ...!!");
            }
        }
        sc.close();
    }
}

class FirstFit {
    void firstFit(int inputBlocks[], int inputProcesses[], int m, int n) {
        int blocks[] = new int[m];
        for (int i = 0; i < inputBlocks.length; i++)
            blocks[i] = inputBlocks[i];
        int processes[] = new int[n];
        for (int i = 0; i < processes.length; i++)
            processes[i] = inputProcesses[i];
        int allocating[] = new int[n];
        for (int i = 0; i < allocating.length; i++)
            allocating[i] = -1;
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < m; i++) {
                if (processes[j] <= blocks[i]) {
                    blocks[i] = blocks[i] - processes[j];
                    allocating[j] = i;
                    break;
                }
            }
        }
        System.out.println("\n\t\t\t Process No.\tProcess Size\tBlock no.");
        for (int i = 0; i < n; i++) {
            System.out.print("\t\t\t " + (i + 1) + "\t\t" +
                    processes[i] + "\t\t");
            if (allocating[i] != -1)
                System.out.print(allocating[i] + 1);
            else
                System.out.print("Not Allocated");
            System.out.println();
        }
    }
}

class NextFit {
    void nextFit(int inputBlocks[], int inputProcesses[], int m, int n) {
        int blocks[] = new int[m];
        for (int i = 0; i < inputBlocks.length; i++)
            blocks[i] = inputBlocks[i];
        int processes[] = new int[n];
        for (int i = 0; i < processes.length; i++)
            processes[i] = inputProcesses[i];
        int allocating[] = new int[n];
        for (int i = 0; i < allocating.length; i++)
            allocating[i] = -1;
        int flag = 0;
        for (int j = 0; j < n; j++) {
            int i = 0;
            if (flag == m - 1)
                i = 0;
            while (i < m) {
                if (processes[j] <= blocks[i]) {
                    blocks[i] = blocks[i] - processes[j];
                    allocating[j] = i;
                    flag = i;
                    break;
                }
                i++;
            }
        }
        System.out.println("\n\t\t\t Process No.\tProcess Size\tBlock no.");
        for (int i = 0; i < n; i++) {
            System.out.print("\t\t\t " + (i + 1) + "\t\t" +
                    processes[i] + "\t\t");
            if (allocating[i] != -1)
                System.out.print(allocating[i] + 1);
            else
                System.out.print("Not Allocated");
            System.out.println();
        }
    }
}

class BestFit {
    void bestFit(int inputBlocks[], int inputProcesses[], int m, int n) {
        int blocks[] = new int[m];
        for (int i = 0; i < inputBlocks.length; i++)
            blocks[i] = inputBlocks[i];
        int processes[] = new int[n];
        for (int i = 0; i < processes.length; i++)
            processes[i] = inputProcesses[i];
        int allocating[] = new int[n];
        for (int i = 0; i < allocating.length; i++)
            allocating[i] = -1;
        for (int j = 0; j < n; j++) {
            int BestBlock = -1;
            for (int i = 0; i < m; i++) {
                if (blocks[i] >= processes[j]) {
                    if (BestBlock == -1)
                        BestBlock = i;
                    else if (blocks[i] < blocks[BestBlock])
                        BestBlock = i;
                }
            }
            if (BestBlock != -1) {
                allocating[j] = BestBlock;
                blocks[BestBlock] = blocks[BestBlock] - processes[j];
            }
        }
        System.out.println("\n\t\t\t Process No.\tProcess Size\tBlock no.");
        for (int i = 0; i < n; i++) {
            System.out.print("\t\t\t " + (i + 1) + "\t\t" +
                    processes[i] + "\t\t");
            if (allocating[i] != -1)
                System.out.print(allocating[i] + 1);
            else
                System.out.print("Not Allocated");
            System.out.println();
        }
    }
}

class WorstFit {
    void worstFit(int inputBlocks[], int inputProcesses[], int m, int n) {
        int blocks[] = new int[m];
        for (int i = 0; i < inputBlocks.length; i++)
            blocks[i] = inputBlocks[i];
        int processes[] = new int[n];
        for (int i = 0; i < processes.length; i++)
            processes[i] = inputProcesses[i];

        int allocating[] = new int[n];
        for (int i = 0; i < allocating.length; i++)
            allocating[i] = -1;
        for (int j = 0; j < n; j++) {
            int MaxBlock = -1;
            for (int i = 0; i < m; i++) {
                if (blocks[i] >= processes[j]) {
                    if (MaxBlock == -1)
                        MaxBlock = i;
                    else if (blocks[i] > blocks[MaxBlock])
                        MaxBlock = i;
                }
            }
            if (MaxBlock != -1) {
                allocating[j] = MaxBlock;
                blocks[MaxBlock] = blocks[MaxBlock] - processes[j];
            }
        }
        System.out.println("\n\t\t\t Process No.\tProcess Size\tBlock no.");
        for (int i = 0; i < n; i++) {
            System.out.print("\t\t\t " + (i + 1) + "\t\t" +
                    processes[i] + "\t\t");
            if (allocating[i] != -1)
                System.out.print(allocating[i] + 1);
            else
                System.out.print("Not Allocated");
            System.out.println();
        }
    }
}
