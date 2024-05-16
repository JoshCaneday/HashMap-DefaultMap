import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.*;

public class FileSystemTest {
	
	@Test
	public void findNoFilesByNameTest() {
		FileSystem test = new FileSystem();
		FileData a = new FileData("Joshua", "Home", "10");
		FileData b = new FileData("Joshua", "Root", "20");
		
		ArrayList<FileData> expected = new ArrayList<FileData>();
		
		FileData[] expect = new FileData[3];
		FileData[] actual = new FileData[3];
		
		assertEquals(expected, test.findFilesByName("Joshua"));
		
	}
	
	@Test
	public void removeByNameTest() {
		FileSystem test = new FileSystem();
		test.add("Joshua", "Home", "10");
		
		
		test.removeByName("Joshua");
		assertEquals(null, test.findFile("Joshua", "Home"));
		
		
	}
	

}
