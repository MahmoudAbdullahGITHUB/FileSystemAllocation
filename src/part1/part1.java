
package part1;


import java.nio.file.*;
import java.io.*;
import java.nio.file.attribute.*;
import java.nio.channels.FileChannel;
import java.nio.ByteBuffer;
import static java.nio.file.StandardOpenOption.*;
import java.util.Scanner;
import java.text.*;
import part1.sys_Extend;

public class part1 {

    public static void main(String[] args) {
        sys_Extend ss = new sys_Extend();

        ss.Add_Folder_To_System("f1", "/root");
        ss.Add_File_To_System("file", "/root/f1", 30);
        ss.DisplayDiskStatus();
        ss.Add_File_To_System("file3367", "/root/f1", 50);
        ss.DisplayDiskStatus();
        ss.Add_File_To_System("file336", "/root/f1", 20);
        ss.DisplayDiskStatus();
        ss.Delete_File_From_System("/root/f1/file");
        ss.DisplayDiskStatus();
        
        ss.Delete_File_From_System("/root/f1/file336");
        ss.DisplayDiskStatus();
        
        
                ss.Add_File_To_System("file22", "/root/f1", 20);
        ss.DisplayDiskStatus();
    }

}
