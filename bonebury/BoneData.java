package bonebury;

public class BoneData {

	
	public enum ids{
		
		BONES(526);
		
		int id;
		ids(int id){
			this.id = id;
			
		}
		public int getID(){
			return id;
		}
		
	}
	
}
