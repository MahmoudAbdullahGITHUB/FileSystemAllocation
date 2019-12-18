package part1;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Folder {

    String Folder_Name;
    String Folder_Path;
    
    int Total_Folder_Size = 0;

    boolean The_Folder_Have_Entity = false;

    List<Folder> The_Folders_That_Exists_IN_This_Folder = new ArrayList<Folder>();
    List<File> The_Files_That_Exists_IN_This_Folder = new ArrayList<File>();

    Folder(String name, String path) {

        Folder_Name = name;
        Folder_Path = path + "/" + name;

    }
    
    
        Folder() {

        Folder_Name = "";
        Folder_Path = "";

    }

    public void Add_Folder(String name) {

        Folder New_Folder = new Folder(name, Folder_Path);
        The_Folders_That_Exists_IN_This_Folder.add(New_Folder);

    }

    public void Add_File(String name, int size, int start_index) {

        File New_File = new File(name, Folder_Path, size, start_index);
        The_Files_That_Exists_IN_This_Folder.add(New_File);
        Total_Folder_Size += size;

    }

    public void Delete_Folder_From_Folder(String path) {
        String[] part = path.split("/");
        String new_path = "";
        for (int i = 0; i < part.length - 1; i++) {
            new_path += part[i];
            if (i != part.length - 2) {
                new_path += '/';
            }
        }
        Folder instance = Search_For_Path(new_path);
        instance.The_Folders_That_Exists_IN_This_Folder.size();
        for (int i = 0; i < instance.The_Folders_That_Exists_IN_This_Folder.size(); i++) {
            if (part[part.length - 1].matches(instance.The_Folders_That_Exists_IN_This_Folder.get(i).Folder_Name)) {
                instance.The_Folders_That_Exists_IN_This_Folder.remove(i);
            }
        }
    }

    public Folder Search_For_Path(String path) {
        Folder Not_Found_Folder = new Folder(":", "NotFound");
        //System.out.println("current = |"+Folder_Path+"| path = |"+path+"|");

        if (path.matches(Folder_Path)) {

            return this;
        }

        for (int i = 0; i < The_Folders_That_Exists_IN_This_Folder.size(); i++) {
            //System.out.println("yyyyyyyyyyyyycurrent = |"+The_Folders_That_Exists_IN_This_Folder.get(i).Folder_Path+"| path = |"+path+"|");

            if (path.matches(The_Folders_That_Exists_IN_This_Folder.get(i).Folder_Path)) {
                //                       System.out.println( "okkkkkkkkk");       

                return The_Folders_That_Exists_IN_This_Folder.get(i);

            }

            Folder This_Folder_Output_Search = The_Folders_That_Exists_IN_This_Folder.get(i).Search_For_Path(path);

            if (path.matches(This_Folder_Output_Search.Folder_Path)) {

                return This_Folder_Output_Search;

            }
        }

        return Not_Found_Folder;

    }

    public void Add_Folder_TO_Path(String path, String folder_name) {
        Folder The_Folder_That_We_Want_Add_TO = this.Search_For_Path(path);

        if (The_Folder_That_We_Want_Add_TO.Folder_Path.contains("NotFound") == true) {
            System.out.println("Sorry this Path doesn't exsist!");

        } else {

            The_Folder_That_We_Want_Add_TO.Add_Folder(folder_name);
            System.out.println("Done");
        }

    }

    public void Add_File_TO_Path(String path, String file_name, int size, int start_index) {

        Folder The_Folder_That_We_Want_Add_TO = this.Search_For_Path(path);

        if (The_Folder_That_We_Want_Add_TO.Folder_Path.contains("NotFound") == true) {
            System.out.println("Sorry this Path doesn't exsist!");

        } else {

            The_Folder_That_We_Want_Add_TO.Add_File(file_name, size, start_index);
            System.out.println("Done");

        }

    }

    public void Delete_File_From_Folder(String path) {
        String[] part = path.split("/");
        String new_path = "";
        for (int i = 0; i < part.length - 1; i++) {
            new_path += part[i];
            if (i != part.length - 2) {
                new_path += '/';
            }
        }
        // System.out.println("xxxcurrent = |"+new_path+"| path = |"+path+"|");

        Folder instance = Search_For_Path(new_path);
        //   System.out.println("xxxcurrent = |"+instance);

        instance.The_Files_That_Exists_IN_This_Folder.size();
        for (int i = 0; i < instance.The_Files_That_Exists_IN_This_Folder.size(); i++) {
            if (path.matches(instance.The_Files_That_Exists_IN_This_Folder.get(i).File_Path)) {
                instance.The_Files_That_Exists_IN_This_Folder.remove(i);
                //     System.out.println( "haaaaaaaaaaaaaaaaaaaaaaaaaaaaa");       
            }
        }
    }

    public File Get_File_By_Path(String path) {
        File Notfound = new File("", "",0,0);
        String[] part = path.split("/");
        String new_path = "";
        for (int i = 0; i < part.length - 1; i++) {
            new_path += part[i];
            if (i != part.length - 2) {
                new_path += '/';
            }
        }
       
        Folder instance = Search_For_Path(new_path);
   
        instance.The_Files_That_Exists_IN_This_Folder.size();
        for (int i = 0; i < instance.The_Files_That_Exists_IN_This_Folder.size(); i++) {
            if (path.matches(instance.The_Files_That_Exists_IN_This_Folder.get(i).File_Path)) {
                return instance.The_Files_That_Exists_IN_This_Folder.get(i);
            }
        }
        return Notfound;
    }

    public void Display_Folder_Structure(String spaces_nubmer) {
        for (int i = 0; i < The_Files_That_Exists_IN_This_Folder.size(); i++) {
            System.out.println(spaces_nubmer + "< " + The_Files_That_Exists_IN_This_Folder.get(i).File_Name + " >");
        }
        for (int i = 0; i < The_Folders_That_Exists_IN_This_Folder.size(); i++) {
            System.out.println(spaces_nubmer + "- " + The_Folders_That_Exists_IN_This_Folder.get(i).Folder_Name);
            The_Folders_That_Exists_IN_This_Folder.get(i).Display_Folder_Structure(spaces_nubmer + "    ");
        }
    }

}
