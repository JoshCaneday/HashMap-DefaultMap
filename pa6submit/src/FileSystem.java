import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileSystem {

    MyHashMap<String, ArrayList<FileData>> nameMap;
    MyHashMap<String, ArrayList<FileData>> dateMap;

    // TODO
    public FileSystem() {
    	nameMap = new MyHashMap<String, ArrayList<FileData>>();
    	dateMap = new MyHashMap<String, ArrayList<FileData>>();
    }

    // TODO
    public FileSystem(String inputFile) {
    	//this also needs some work
        // Add your code here
    	nameMap = new MyHashMap<String, ArrayList<FileData>>();
    	dateMap = new MyHashMap<String, ArrayList<FileData>>();
    	
    	
        try {
            File f = new File(inputFile);
            Scanner sc = new Scanner(f);
            while (sc.hasNextLine()) {
                String[] data = sc.nextLine().split(", ");
                System.out.println(data[0] + "RAHAHA" + data[1] + "BAHA" + data[2]);
                
                
                // Add your code here
                
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

    // TODO
    public boolean add(String fileName, String directory, String modifiedDate) {
    	
    	boolean added = false;
    	FileData a = new FileData(fileName, directory, modifiedDate);
    	ArrayList<String> all = new ArrayList<String>();
    	for (String z : dateMap.keys())
    	{
    		all.add(z);
    	}
    	ArrayList<FileData> name = new ArrayList<FileData>();
    	ArrayList<FileData> date = new ArrayList<FileData>();
    	if (nameMap.get(fileName) != null)
    	{
    		for (FileData e : nameMap.get(fileName))
    		{
    			if (e.dir.equals(directory))
    			{
    				return false;
    			}
    		}
    		
    		name = nameMap.get(fileName);
    		name.add(a);
    		added = true;
    	}
    	else
    	{
    		name.add(a);
    		nameMap.put(fileName, name);
    		added = true;
    	}
    	
    	if (dateMap.get(modifiedDate) != null)
    	{
    		for (FileData e : dateMap.get(modifiedDate))
    		{
    			if (e.dir.equals(directory))
    			{
    				return false;
    			}
    		}
    		
    		date = dateMap.get(modifiedDate);
    		date.add(a);
    		added = true;
    	}
    	else
    	{
    		date.add(a);
    		dateMap.put(modifiedDate, date);
    		added = true;
    	}
    	
//    	if (dateMap.get(modifiedDate) != null)
//    	{
//    		for (int i = 0; i < all.size(); i++)
//    		{
//    			for (FileData e: dateMap.get(all.get(i)))
//    			{
//    				if (e.dir.equals(directory) && e.name.equals(fileName));
//        			{
//        				return false;
//        			}
//    			}
//    		}
//    		
//    		date = dateMap.get(modifiedDate);
//    		date.add(a);
//    		added = true;
//    	}
//    	else
//    	{
//    		date.add(a);
//    		dateMap.put(modifiedDate, date);
//    		added = true;
//    	}
    	return added;
    	

    }

    // TODO
    public FileData findFile(String name, String directory) {
    	
    	if (nameMap.containsKey(name))
    	{
    		for (FileData e : nameMap.get(name))
    		{
    			if (e.dir.equals(directory) && e.name.equals(name))
    			{
    				
    				return e;
    			}
    		}
    	}
    	else
    	{
    		return null;
    	}
    	return null;

    }

    // TODO
    public ArrayList<String> findAllFilesName() {
    	ArrayList<String> a = new ArrayList<>();
    	
    	for (String e : nameMap.keys())
    	{
    		a.add(e);
    	}
    	return a;
    }

    // TODO
    public ArrayList<FileData> findFilesByName(String name) {
    	ArrayList<FileData> a = new ArrayList<>();
    	if (nameMap.get(name) != null)
    	{
    		for (int i = 0; i < nameMap.get(name).size(); i++)
        	{
        		
        		a.add(nameMap.get(name).get(i));
        		
        	}
    	}
    	
    	return a;

    }

    // TODO
    public ArrayList<FileData> findFilesByDate(String modifiedDate) {
    	ArrayList<FileData> a = new ArrayList<FileData>();
    	if (dateMap.get(modifiedDate) != null)
    	{
    		for (int i = 0; i < dateMap.get(modifiedDate).size(); i++)
        	{
        		a.add(dateMap.get(modifiedDate).get(i));
        	}
    	}
    	
    	return a;
    }

    // TODO
    public ArrayList<FileData> findFilesInMultDir(String modifiedDate) {
    	
    	ArrayList<FileData> submit = new ArrayList<FileData>();
    	ArrayList<FileData> checker = new ArrayList<FileData>();
    	FileData z;
    	
    	for (FileData b: dateMap.get(modifiedDate))
    	{
    		checker.add(b);
    	}
    	for (int i = 0; i < checker.size(); i++)
    	{
    		for (int j = i; j < checker.size(); j++)
    		{
    			if (checker.get(i).name.equals(checker.get(j).name) && !checker.get(i).dir.equals(checker.get(j).dir)) {
    				submit.add(checker.get(i));
    				submit.add(checker.get(j));
    			}
    		}
    	}
    	return submit;
    }

    // TODO
    public boolean removeByName(String name) {
    	ArrayList<String> names = new ArrayList<String>();
    	
    	
    	boolean removed = false;
    	if (nameMap.get(name) != null)
    	{
    		this.nameMap.remove(name);
    		removed = true;
    	}
    	
    	
    	for (String e : dateMap.keys())
    	{
    		names.add(e);
    	}
    	for (int i = 0; i < names.size(); i++)
    	{
    		if (dateMap.get(names.get(i)) != null)
    		{
    			for (FileData e : dateMap.get(names.get(i)))
        		{
        			if (e.name.equals(name))
        			{
        				dateMap.remove(names.get(i));
        				removed = true;
        			}
        		}
    		}
    		
    	}
    	return removed;
    }

    // TODO
    public boolean removeFile(String name, String directory) {
    	
    	
    	
    	ArrayList<String> names = new ArrayList<String>();
    	
    	
    	boolean removed = false;
    	if (nameMap.get(name) == null)
    	{
    		return false;
    	}
    	else
    	{
    		for (FileData e : nameMap.get(name))
    		{
    			if (e.dir.equals(directory))
    			{
    				this.nameMap.remove(name);
    	    		removed = true;
    			}
    		}
    		
    	}
    	
    	
    	for (String e : dateMap.keys())
    	{
    		names.add(e);
    	}
    	for (int i = 0; i < names.size(); i++)
    	{
    		if (dateMap.get(names.get(i)) != null)
    		{
    			for (FileData e : dateMap.get(names.get(i)))
        		{
        			if (e.name.equals(name) && e.dir.equals(directory))
        			{
        				
        				dateMap.remove(names.get(i));
        				removed = true;
        			}
        		}
    		}
    		
    	}
    	return removed;
    	
    	
    	
//    	boolean removed = false;
//    	ArrayList<String> names = new ArrayList<String>();
//    	if (name != null && directory != null)
//    	{
//    		if (nameMap.get(name) == null)
//    		{
//    			return false;
//    		}
//    		for (FileData e: nameMap.get(name))
//        	{
//        		if (e.dir.equals(directory))
//        		{
//        			nameMap.remove(name);
//        			removed = true;
//        		}
//        	}
//        	for (String e : dateMap.keys())
//        	{
//        		names.add(e);
//        	}
//        	for (int i = 0; i < names.size(); i++)
//        	{
//        		for (FileData e : dateMap.get(names.get(i)))
//        		{
//        			if (e.name.equals(name) && e.dir.equals(directory))
//        			{
//        				dateMap.remove(names.get(i));
//        				return true;
//        			}
//        		}
//        		
//        	}
//    	}
//    	
//    	return removed;
    }

}
