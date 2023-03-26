# 3rd Week Lectures  
  
This document summarizes 3rd Week lectures.  
The copyright is at Princeton University and Coursera.  

이 문서는 3주차 강의를 요약, 정리한 것입니다. 모든 저작권은 프린스턴 대학교와 Coursera에 있습니다.  

***  
### MergeSort  
3주차 1차시 - MergeSort  
3주차 2차시 - QuickSort  
#### 0. Overview  
이번 주차의 강의는 약 50년 전 개발됐지만 사실상 모든 소프트웨어 시스템과 연구에 사용되는 여전히 중요하고 의미가 있는 두 가지 클래식한 알고리즘에 기반합니다. 이번 강의에서는 이런 방법이 효율적인 이유를 설명하는 수학적 모델부터 현대 시스템의 실제 응용 프로그램에 적용하는 세부사항을 다룹니다.  
__Lecture : MergeSort__ : 1차시 강의인 병합정렬에서는 병합정렬 알고리즘을 공부하고 이것이 거의 $nlgn$ 번의 비교로 $n$개의 요소가 있는 모든 배열을 정렬할 수 있음을 보여줍니다.  
또한, 비재귀적인 bottom-up 버전을 고려합니다. 우리는 모든 비교 기반의 알고리즘들이 가장 최악의 경우 $nlgn$ 시간이 걸린다는 것을 증명합니다.  
__Lecture : QuickSort__ : 무작위 퀵 정렬을 소개하고 구현하며 이것의 성능을 분석합니다. 또한, k번째 가장 작은 요소를 선형시간 안에 찾는 변형된 퀵 정렬인 __무작위 퀵 선택__ 을 고려합니다.  
마지막으로, 중복된 키가 존재할 때 잘 작동하는 3-way quickort를 다룹니다.  
  
***  
#### 1. MergeSort  
> ##### 1. MergeSort  
> 기본적으로 병합정렬, 합병정렬은 반복적으로 배열을 둘로 나누고 정렬한 다음 결과들을 합치는 방식으로 정렬하는 것을 의미합니다.  
> 보조배열을 사용하여 $a[low]$ ~ $a[mid]$ 와 $a[mid+1]$ ~ $a[high]$ 로 쪼갭니다. 그 다음 두 배열을 정렬하여 합치는 방식으로 진행됩니다.  
> low는 배열의 가장 앞부분이고, high는 배열의 가장 뒷부분입니다.  
> __실습코드__  
> ```java
> private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
>   assert isSorted(a, lo, mid);
>   assert isSorted(a, mid+1, hi);
>   
>   for (int k = lo; k <= hi; k++) {
>     aux[k] = a[k];
>   }
>   
>   int i = lo, j = mid+1;
>   for (int k = lo; k <= hi; k++) {
>     if (i > mid) a[k] = aux[j++];
>     else if (j > hi) a[k] = aux[i++];
>     else if (less(aux[j], aux[i])) a[k] = aux[j++];
>     else a[k] = aux[i++];
>   }
>   assert isSorted(a, lo, hi);
> }
> ```  
> 가장 처음으로 해야할 것은 보조 배열에 모든 것을 복사하는 것입니다.  
> 그 다음 i는 lo(가장 작은 값)부터 mid(중간에 있는 값)까지 움직이고, j는 mid+1부터 hi(가장 큰 값)까지 움직이면서 원래의 배열에 값을 넣습니다.  
>   
> __mergesort의 성능__  
> $O(nlogn)$ 시간이 걸리기 때문에 insertion sort (삽입 정렬)을 사용할 때보다 훨씬 빠르게 정렬할 수 있습니다.  
> ##### 2. Bottom-up MergeSort  
> 합병 정렬의 상향 버전입니다. 이 버전은 반복이 없고, 이해하고 코딩하는 것이 매우 간단합니다.  
> 기본 개념은 사이즈가 1인 정렬된 하위 배열을 합쳐서 2, 4, 8, 16 이런 식으로 합치면서 올라가는 방식입니다.
