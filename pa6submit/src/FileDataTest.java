import static org.junit.Assert.*;

import org.junit.*;

public class FileDataTest {
	
	
	@Test
	public void constructorWorks()
	{
		FileData test = new FileData("Joshua", "/root/People", "11/15/2023");
		String nameExpected = "Joshua";
		String pathExpected = "/root/People";
		String dateExpected = "11/15/2023";
		assertEquals(nameExpected, test.name);
		assertEquals(pathExpected, test.dir);
		assertEquals(dateExpected, test.lastModifiedDate);
	}
	
	@Test
	public void toStringWorks()
	{
		FileData test = new FileData("Caneday", "/home/Users", "11/15/2023");
		String expected = "{Name: Caneday, Directory: /home/Users, Modified Date: 11/15/2023}";
		assertEquals(expected, test.toString());
	}
	

}
