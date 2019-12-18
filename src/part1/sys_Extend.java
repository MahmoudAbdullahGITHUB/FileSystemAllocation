package part1;

public class sys_Extend {

    int The_Total_Size_OF_The_System = 100;
    int The_Avabiale_Size_OF_The_System = 100;
    boolean Begin_To_use_Best_Fit_Allocation = false;
    Block Blocks[] = new Block[The_Total_Size_OF_The_System + 1];
    int Extend = 5;
    Folder root = new Folder("root", "");

    public sys_Extend copysystem(sys_Extend s) {
        sys_Extend New_system = new sys_Extend();
        for (int i = 0; i < s.The_Total_Size_OF_The_System + 1; i++) {
            Block block = new Block();
            New_system.Blocks[i].Allocated = s.Blocks[i].Allocated;
            New_system.Blocks[i].End_Index = s.Blocks[i].End_Index;
            New_system.Blocks[i].Start_Index = s.Blocks[i].Start_Index;
            New_system.Blocks[i].start_of_the_next_extend = s.Blocks[i].start_of_the_next_extend;
        }
        New_system.Begin_To_use_Best_Fit_Allocation = s.Begin_To_use_Best_Fit_Allocation;
        New_system.Extend = s.Extend;
        New_system.The_Avabiale_Size_OF_The_System = s.The_Avabiale_Size_OF_The_System;
        New_system.The_Total_Size_OF_The_System = s.The_Total_Size_OF_The_System;
        New_system.root = s.root;
        return New_system;
    }

    public void Return_to_the_System(sys_Extend s, sys_Extend New_system) {
        for (int i = 0; i < s.The_Total_Size_OF_The_System + 1; i++) {
            Block block = new Block();
            New_system.Blocks[i].Allocated = s.Blocks[i].Allocated;
            New_system.Blocks[i].End_Index = s.Blocks[i].End_Index;
            New_system.Blocks[i].Start_Index = s.Blocks[i].Start_Index;
            New_system.Blocks[i].start_of_the_next_extend = s.Blocks[i].start_of_the_next_extend;
        }
        New_system.Begin_To_use_Best_Fit_Allocation = s.Begin_To_use_Best_Fit_Allocation;
        New_system.Extend = s.Extend;
        New_system.The_Avabiale_Size_OF_The_System = s.The_Avabiale_Size_OF_The_System;
        New_system.The_Total_Size_OF_The_System = s.The_Total_Size_OF_The_System;
        New_system.root = s.root;
    }

    public int round(int x) {
        return (int) (x + 0.5);
    }

    sys_Extend() {

        for (int i = 0; i < The_Total_Size_OF_The_System + 1; i++) {
            Block block = new Block();
            Blocks[i] = block;
        }
    }

    public int add(int size) {
        int extendss = round(size / Extend);
        sys_Extend new_sys = this.copysystem(this);
        Block Extends[] = new Block[extendss];
        for (int i = 0; i < extendss; i++) {
            Block block = new Block();
            Extends[i] = block;
        }
        int completed = -1;
        for (int i = 0; i < extendss; i++) {
            int index = -1;
            if (Begin_To_use_Best_Fit_Allocation == true) {
                      //                       System.out.println("wwwwwwwwwwwwwwwwww" +index);

                index = Get_Start_Block_Index_After_Deallocation1(Extend);
                    //         System.out.println("gggggggggg" +index);
            } else {
                index = Get_Start_Block_Index_Before_Deallocation(Extend);
            }
            //       this.DisplayDiskStatus();
            if (index != -1) {
                // System.out.println("qqqqqqqqqq"+this.Blocks[index].Start_Index);

                Extends[i] = this.Blocks[index];
                //               System.out.println("qqqqqqqqqq222222222 "+Extends[i].Start_Index);
//                Extends[i].End_Index = this.Blocks[index].End_Index;
//                Extends[i].Start_Index = this.Blocks[index].Start_Index;
//                //Extends[i]. = Blocks[index];
                completed = 1;

            } else {
                completed = -1;
                Return_to_the_System(new_sys, this);
                break;
            }
          //  this.DisplayDiskStatus();
        }
        if (completed == 1) {
            Extends[extendss - 1].start_of_the_next_extend = -1;
            for (int i = 0; i < extendss - 1; i++) {
                // System.out.println("os2_assign2_p1.system_extend.add()"+Blocks[i + 1].Start_Index);
                Extends[i].start_of_the_next_extend = Extends[i + 1].Start_Index;
                // System.out.println("cccccccccccccccc"+Extends[i].start_of_the_next_extend+" sssss "+i);

            }
            return Extends[0].Start_Index;
        }
        return completed;
    }

    public void Add_Folder_To_System(String name, String path) {
        root.Add_Folder_TO_Path(path, name);
    }

    public void Allocat_Blocks(int start, int end) {

        for (int i = start; i <= end; i++) {
            Blocks[i].Allocated = true;
            Blocks[i].Start_Index = start;
            Blocks[i].End_Index = end;
        }
    }

    public int Get_Start_Block_Index_Before_Deallocation(int size) {

        if (size > The_Avabiale_Size_OF_The_System) {
            return -1;
        }
        for (int i = 1; i < The_Total_Size_OF_The_System + 1; i++) {

            if (Blocks[i].Allocated == false) {
                if (i + size - 1 < The_Total_Size_OF_The_System + 1) {

                    if (Blocks[i + size - 1].Allocated == false) {

                        this.Allocat_Blocks(i, i + size - 1);
//                        System.out.println("start" + i);

                        return i;

                    } else {

                        i += Blocks[i + size - 1].End_Index;

                    }

                } else {

                    i += Blocks[i].End_Index;

                }
            }
        }

        return -1;

    }

    public int Get_Start_Block_Index_After_Deallocation(int size) {


        if (size > The_Avabiale_Size_OF_The_System) {
            return -1;
        }
        int alloc = -1;

        for (int i = 1; i < The_Total_Size_OF_The_System + 1; i++) {

            if (Blocks[i].Allocated == false) {
                alloc = i;
                while (i < The_Total_Size_OF_The_System + 1 && Blocks[i].Allocated == false) {

                    if (i - alloc + 1 == size) {
//                                    System.out.println("alloc = "+alloc);

                        this.Allocat_Blocks(alloc, Extend + i);
                        return alloc;
                    }
                    i++;
                }
                alloc = -1;
            }
        }

        return alloc;
    }

    public int Get_Start_Block_Index_After_Deallocation1(int size) {

        if (size > The_Avabiale_Size_OF_The_System) {
            return -1;
        }
        int alloc = -1;
        int start = -1, end = The_Total_Size_OF_The_System;
        int comparation_start = 1, comparation_end = The_Total_Size_OF_The_System;

        for (int i = 1; i < The_Total_Size_OF_The_System + 1; i++) {

            if (Blocks[i].Allocated == false) {
                comparation_start = i;
                while (i < The_Total_Size_OF_The_System + 1&&Blocks[i].Allocated == false) {
                    i++;
                }
                comparation_end = i;
     //            System.out.println("comparation_end" +comparation_start);


                if ((comparation_end - comparation_start >= size)&&comparation_end - comparation_start < end - start ) {

                    end = comparation_end;
                    start = comparation_start;
                    alloc = 1;
                }
            }
        }
       //             System.out.println("comparation_sssss" +comparation_start);

        if (start != -1 && alloc == 1) {
//            System.out.println("comparation_S " +comparation_start);
//            System.out.println("comparation_E " +(comparation_start+Extend));

            this.Allocat_Blocks(start, start+Extend-1);

        }
        else{start=-1;}

        return start;

    }

    public void Add_File_To_System(String name, String path, int size) {
        int index = add(size);

        if (index == -1) {
            System.out.println("sorry no space");

        } else {
            The_Avabiale_Size_OF_The_System -= size;
            root.Add_File_TO_Path(path, name, size, index);
            //  root.Add_File(name, size, index);
        }
    }

    public void Delete_File_From_System(String path) {

        File file = root.Get_File_By_Path(path);
        if (file.File_Path != "") {
            int extendss = round(file.File_Size / Extend);
            int next_extend = file.File_Start_Block_Index;
            for (int i = 0; i < extendss; i++) {
                // System.out.println("sssssssssss"+next_extend);
                int next_extend2 = Blocks[next_extend].start_of_the_next_extend;
                this.Delete_Extend(next_extend, Extend);
                next_extend = next_extend2;
            }
            this.The_Avabiale_Size_OF_The_System += file.File_Size;
            root.Delete_File_From_Folder(path);
            this.Begin_To_use_Best_Fit_Allocation = true;
        }
    }

    public void Delete_Extend(int start, int size) {
        int end = start + size - 1;
        for (int i = start; i <= end; i++) {
            Blocks[i].Allocated = false;
            Blocks[i].Start_Index = i;
            Blocks[i].End_Index = i;
            Blocks[i].start_of_the_next_extend = i;;
        }
    }

    public void Delete_Folder(String path) {
        Folder folder = root.Search_For_Path(path);
        Delete_all_Files_On_All_Folder(folder);
        Delete_File_From_System(path);
        root.Delete_Folder_From_Folder(path);
    }

    public void Delete_all_Files_On_All_Folder(Folder folder) {
        int size = folder.The_Files_That_Exists_IN_This_Folder.size();

        while (size > 0) {
            Delete_File_From_System(folder.The_Files_That_Exists_IN_This_Folder.get(0).File_Path);
            size = folder.The_Files_That_Exists_IN_This_Folder.size();
        }
        for (int i = 0; i < folder.The_Folders_That_Exists_IN_This_Folder.size(); i++) {
            Delete_all_Files_On_All_Folder(folder.The_Folders_That_Exists_IN_This_Folder.get(i));
        }
    }

    public void DisplayDiskStatus() {
        System.out.println("The empty space " + The_Avabiale_Size_OF_The_System);
        System.out.println("The Allocated space " + (The_Total_Size_OF_The_System - The_Avabiale_Size_OF_The_System));
        int new_line_counter = 0;
        for (int i = 1; i < Blocks.length; i++) {
            if (Blocks[i].Allocated == false) {
                System.out.print(" 0 ");
            } else {
                System.out.print(" 1 ");
            }
            new_line_counter++;
            if (new_line_counter == 10) {
                System.out.println("");
                new_line_counter = 0;
            }
        }
    }
}
