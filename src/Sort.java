/**
 * 常用排序算法合集
 */
public class Sort {
    //二分查找  在array找formIndex到toIndex之间的数，有没有key这个值
    public static int binarySearch(int[] nums, int formIndex, int toIndex, int key) {
        int low = formIndex;
        int high = toIndex - 1;

        while (low <= high) {
            int mid = (low + high) >>> 1;
            int temp = nums[mid];
            if (temp == key) {
                return mid;
            } else if (temp > key) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return -(low + 1);
    }

    //快速排序   12  21  31  68  59  40       x=31
    public static void quickSort(int[] array, int begin, int end) {
        if (begin > end) {
            return;
        }
        int low = begin;
        int high = end;
        int temp = array[begin];
        while (low != high) {
            while (high > low && array[high] >= temp) {
                high--;
            }
            while (low < high && array[low] <= temp) {
                low++;
            }
            if (low < high) {
                int t = array[low];
                array[low] = array[high];
                array[high] = t;
            }
        }
        array[begin] = array[low];
        array[low] = temp;
        quickSort(array, begin, low - 1);
        quickSort(array, low + 1, end);
    }

    //归并合并    0    4    7
    public static void merge(int[] array, int left, int mid, int right) {
        int leftSize = mid - left;
        int rightSize = right - mid + 1;
        int leftArray[] = new int[leftSize];
        int rightArray[] = new int[rightSize];
        //填充左边的数组
        for (int i = left; i < mid; i++) {
            leftArray[i - left] = array[i];
        }
        //填充右边的数组
        for (int i = mid; i <= right; i++) {
            rightArray[i - mid] = array[i];
        }
        //合并数组
        int i = 0;
        int j = 0;
        int k = left;
        while (i < leftSize && j < rightSize) {
            if (leftArray[i] < rightArray[j]) {
                array[k++] = leftArray[i++];
            } else {
                array[k++] = rightArray[j++];
            }
        }
        while (i < leftSize) {//左边还有数据没用完
            array[k++] = leftArray[i++];
        }
        while (j < rightSize) {//右边还有数据没用完
            array[k++] = rightArray[j++];
        }
    }


    //归并方法
    public static void mergeSort(int array[], int left, int right) {
        if (left >= right) {
            return;
        }
        //右边必须加一，如果是左边减一则会出现死循环，要始终保证左边小于等于右边
        int mid = (right + left) >>> 1;
        mergeSort(array, left, mid);
        mergeSort(array, mid + 1, right);
        merge(array, left, mid + 1, right);
    }


    //循环比赛日程安排示例
    public static int[][] schedule(int k) {
        int[][] schedule = new int[k][k];
        for (int i = 0; i < k; i++) {
            schedule[0][i] = i + 1;
        }
        for (int r = 1; r < k; r <<= 1) {
            for (int i = 0; i < k; i += 2 * r) {
                Copy(schedule, r, r + i, 0, i, r);
                Copy(schedule, r, i, 0, r + i, r);
            }
        }

        return schedule;
    }

    /*
    tox:目标数组的行号
    toy:目标数组的列号
    fromx:源数组的行号
    fromy:源数组的列号
    r:数组的大小为 r*r
    */
    public static void Copy(int[][] schedule, int tox, int toy, int fromx, int fromy, int r) {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < r; j++) {
                schedule[tox + i][toy + j] = schedule[fromx + i][fromy + j];
            }
        }
    }


    public static void main(String[] args) {
//        int[] array=new int[]{1,2,4,9,13,20,22,29,34,35};
//        int key=35;
//        System.out.println(binarySearch(array,0,array.length,key));

//        int[] array = new int[]{1, 3, 5, 7, 9, 4, 8, 10};
//        quickSort(array, 0, array.length - 1);
//        for (int i = 0; i < array.length; i++) {
//            System.out.print(array[i] + " ");
//        }

//        int[] array=new int[]{12,1,3,5,11,7,9,4,8,10,2,6};
////        merge(array,0,5,array.length-1);
//        mergeSort(array,0,array.length-1);
//        for(int i=0;i<array.length;i++){
//            System.out.print(array[i]+" ");
//        }

        int[][] schedule = schedule(8);
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(schedule[i][j] + " ");
            }
            System.out.println();
        }
    }
}
