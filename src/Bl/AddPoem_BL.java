package Bl;

import java.io.IOException;

import Dal.AddPoem_DAL;

public class AddPoem_BL {

	public void add_poem_bl() throws IOException {
		AddPoem_DAL dao = new AddPoem_DAL();
		dao.add_poem(null);
	}
	/*public static void main(String[] args) throws IOException {
		 AddPoem_BL obj = new AddPoem_BL();
		 obj.add_poem_bl();
	}*/

}
