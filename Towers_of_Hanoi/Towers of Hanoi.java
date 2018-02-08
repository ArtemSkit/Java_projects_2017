/*
 * CSCI 2336 Programming Fundamentals III Fall 2017
 * Artem Skitenko
 * 9/9/2017
 * Towers of Hanoi
 */

public class HelloWorld {

    public static void showArr(int[] one, int[] two, int[] three){

        for (int i = 0; i < 10; i++) {
            System.out.println(String.format("%20d%20d%20d", one[i], two[i], three[i]));
        }
    }

    public static int moveBagel(int[] arrTarget, int[] arrSource, int sourceElem) {
        if (arrSource[sourceElem]==0) {
            System.out.println(String.format("tried to move 0 from index %d", sourceElem));
            return -1;
        }
        if (sourceElem > 0 && arrSource[sourceElem - 1] != 0) {
            System.out.println(String.format("pulling from the middle %d from index %d in arr %h", arrSource[sourceElem], sourceElem, arrSource));
        }
        int ind=9;
        for (int i= 0; i < 10; i++) {
            if (arrTarget[i] != 0) {
                if (arrTarget[i] < arrSource[sourceElem]) {
                    System.out.println(String.format("tried to move over smaller %d on %d", arrSource[sourceElem], arrTarget[i]));
                    return -1;
                }
                ind = i - 1;
                break;
            }
        }
        System.out.println(String.format("moved number %d at ind %d from arr %h to arr %h at ind %d", arrSource[sourceElem], sourceElem, arrSource, arrTarget, ind));
        arrTarget[ind]=arrSource[sourceElem];
        arrSource[sourceElem]=0;
        return ind;
    }

    public static int solution(int[] arrTarget, int[] arrSource, int[] arrAux, int sourceInd) {
        //System.out.println(String.format("solution moving %d, at ind %d from arr %h to %h", arrSource[sourceInd], sourceInd, arrSource, arrTarget));
        if (sourceInd==0){
            return moveBagel(arrTarget, arrSource, sourceInd);
        }else if (arrSource[sourceInd-1]==0) {
            return moveBagel(arrTarget, arrSource, sourceInd);
        } else {
            int tempIndex=solution(arrAux, arrSource, arrTarget, sourceInd - 1);
            int tempIndex1=moveBagel(arrTarget, arrSource, sourceInd);
            solution(arrTarget, arrAux, arrSource, tempIndex);
            return tempIndex1;
        }
    }


    public static void main(String[] args) {
        int ARR_SIZE=10;
        int[] one=new int[ARR_SIZE], two=new int[ARR_SIZE], three=new int[ARR_SIZE];

        for (int i=9; i>=0; i--) {
            two[i]=i+1;
            one[i]=0;
            three[i]=0;
        }
        showArr(one, two, three);
        solution(three,two,one,9);
        showArr(one, two, three);


    }
}
