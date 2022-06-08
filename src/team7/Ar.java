package team7;

/**
 *
 * @author 
 */
public class Ar {
    
    static int memoryCount = 0;
    static int totalCount = 0; 
    static int size = 1000;
    static public int[] memory = new int[size];
    static public int enemymode = 0;
    
    
    public static void setArray(int enemymove) {
        if(memoryCount==size) {
            resetArray(size/2);
        }
        memory[memoryCount] = enemymove;
        memoryCount++;
        totalCount++;
    }
    
    
    public static void resetArray(int half) {
        int[] retemp = new int[half];
        System.arraycopy(memory, 500, retemp, 0, 500);
        System.arraycopy(retemp, 0, memory, 0, 500);
        memoryCount = half;
    }
    
    
    public static void rememberEnemymode(int mymode) {
      if (mymode == 0) {
          enemymode = 1;
      }
      else if (mymode == 1) {
          enemymode = 0;
      }
   }
    
    
   public static int[] getRecentRSP(int num) {
       int[] get = new int[num];
       int count = 0;
       for (int i = memoryCount-1; i>memoryCount-num-1; i--) {
           get[count] = memory[i];
           count++;
       }
       return get;
   }
   
   public static int getEnemymode() {
       return enemymode;
   }
}