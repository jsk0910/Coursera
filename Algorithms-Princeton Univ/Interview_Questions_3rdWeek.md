### Interview Questions on 3rd Week  
This document is for Interview Questions on Coursera - Algorithms Part I (Princeton Univ.).  
Because this task goes through similarity verification, copying can cause problems.  
copyright of every reference is from Princeton University and Coursera.  
-> 모든 문제와 자료의 출처는 Princeton University와 Coursera에 있습니다.  
  
> #### 1. MergeSort  
> > 1. __Merging with smaller auxiliary array.__ : Suppose that the subarray $a[0]$ to $a[n - 1]$ is sorted and the subarray $a[n]$ to $a[2 * n - 1]$ is sorted. How can you merge the two subarray so that $a[0]$ to $a[2 * n - 1]$ is sorted using an auxiliary array of length $n$ (instead of $2n$)?  
> > -> 작은 보조 배열로 병합하기 : $a[0]$ 부터 $a[n - 1]$ 까지가 정렬된 부분집합과 $a[n]$ 부터 $a[2 * n - 1]$ 까지가 정렬된 부분집합이 있다고 가정한다. 길이 $n$ 의 보조 배열을 사용하여 $a[0]$ 부터 $a[2 * n - 1]$ 까지가 정렬되도록 두 부분집합을 병합하는 방법은 무엇인가?  
> > + 제가 쓴 정답 : copy only left half into auxiliary array, then merge directly to the original array.  
> > + 실제 정답  
> > 2. __Counting inversions.__ : An inversion in an array $a[]$ is a pair of entries $a[i]$ and $a[j]$ such that $i < j$ but $a[i] > a[j]$. Given an array, design a linearithmic algorithm to count the number of inversions.  
> > -> 배열 $a[]$의 반전은 $i < j$일때 $a[i] > a[j]$인 $a[i]$와 $a[j]$의 쌍입니다. 배열이 주어졌을때, 반전의 수를 계산하는 알고리즘을 고안하시오.  
> > + 제가 쓴 정답  
> > Use mergesort, sum the position leap each element of the right subarray whenever it's moved to the auxiliary array before the left subarray index comes to end.  
> > + 실제 정답  
> > 3. __Shuffling a linked list.__ : Given a singly-linked list containing $n$ items, rearrange the items uniformly at random. Your algorithm should consume a logarithmic (or constant) amount of extra memory and run in time proportional to $nlogn$ in the worst case.  
> > -> 
