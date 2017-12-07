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
        if (begin >= end) {
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

    //三路快排
    public static void threeQuickSort(int[] array, int begin, int end) {
        if (begin >= end) {
            return;
        }

          /*
     * 工作指针
     * q指向序列左边等于pivot元素的位置
     * p指向序列右边等于Pivot元素的位置
     * low指向从左向右扫面时的元素
     * high指向从右向左扫描时的元素
     */
        int low = begin;
        int high = end;
        int q = begin;
        int p = end;
        /*
         * 每次总是取序列最右边的元素为锚点
         */
        int pivot = array[begin];
        while (low != high) {
         /*
         * 工作指针high从右向左不断扫描，找小于或者等于锚点元素的元素
         */
            while (high > low && pivot <= array[high]) {
             /*
             * 找到与锚点元素相等的元素将其交换到p所指示的位置
             */
                if (pivot == array[high]) {
                    swap(array, p, high);
                    p--;
                }
                high--;
            }
            /*
            * 工作指针low从左向右不断扫描，找大于或者等于锚点元素的元素
            */
            while (high > low && pivot >= array[low]) {
             /*
             * 找到与锚点元素相等的元素将其交换到q所指示的位置
             */
                if (pivot == array[low]) {
                    swap(array, q, low);
                    q++;
                }
                low++;
            }
        /*
         * 将左边大于pivot的元素与右边小于pivot元素进行交换
         */
            if (low < high) {
                swap(array, low, high);
            }
        }
 /*
     * 因为工作指针high指向的是比描点大的值所以往后移动一个位置
     * p指向的是当前需要处理元素的上一个元素，故而需要向后到当前元素的实际位置，然后将等于pivot元素交换到序列中间
     */
        high++;
        p++;
        while (p <= end && p != high) {
            swap(array, p++, high++);
        }
        /*
     * 因为工作指针q指向的是当前需要处理元素的下一个元素
     * 故而需要退回到当前元素的实际位置，然后将等于pivot元素交换到序列中间
     */
        q--;
        while (q >= begin && q != low) {
            swap(array, q--, low--);
        }
    /*
     * 递归遍历左右子序列
     */
        threeQuickSort(array, begin, low);
        threeQuickSort(array, high, end);
    }

    public static void swap(int[] a, int src, int dest) {
        int t = a[dest];
        a[dest] = a[src];
        a[src] = t;
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

//        int[][] schedule = schedule(8);
//        for (int i = 0; i < 8; i++) {
//            for (int j = 0; j < 8; j++) {
//                System.out.print(schedule[i][j] + " ");
//            }
//            System.out.println();
//        }

        int[] p = {1, 2, 1, 2, 1, 3, 3, 3, 2};//{7,1,7,1,2,7,2,3,1,3,1,3,4,5,6,0,5,0,0,6};//
        threeQuickSort(p, 0, p.length - 1);
        for (int i = 0; i < p.length; i++) {
            System.out.print(p[i] + " ");
        }
    }
}
