public class square {
    public static void dSquareList(IntList L){
        while(L!=null){
            L.first=L.first*L.first;
            L=L.rest;
        }
    }

    public static void main(String[] args) {
        IntList origL = IntList.of(1, 2, 3);
        dSquareList(origL);

    }
}
