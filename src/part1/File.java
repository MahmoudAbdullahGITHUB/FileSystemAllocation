package part1;

public class File {

    String File_Name;
    String File_Path;
    int File_Size;
    int File_Start_Block_Index;

    File(String name, String path, int size, int index) {
        File_Name = name;
        File_Path = path + "/" + name;
        File_Size = size;
        File_Start_Block_Index = index;
    }
        File() {
        File_Name ="";
        File_Path = "";
        File_Size = 0;
        File_Start_Block_Index = 0;
    }

}
