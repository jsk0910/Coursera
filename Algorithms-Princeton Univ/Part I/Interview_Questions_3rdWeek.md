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
> > ![image](https://user-images.githubusercontent.com/23286838/227817647-74f8b8c3-6ad7-4fd3-abc6-76342cb78c3f.png)  
> > 2. __Counting inversions.__ : An inversion in an array $a[]$ is a pair of entries $a[i]$ and $a[j]$ such that $i < j$ but $a[i] > a[j]$. Given an array, design a linearithmic algorithm to count the number of inversions.  
> > -> 배열 $a[]$의 반전은 $i < j$일때 $a[i] > a[j]$인 $a[i]$와 $a[j]$의 쌍입니다. 배열이 주어졌을때, 반전의 수를 계산하는 알고리즘을 고안하시오.  
> > + 제가 쓴 정답  
> > Use mergesort, sum the position leap each element of the right subarray whenever it's moved to the auxiliary array before the left subarray index comes to end.  
> > + 실제 정답  
> > ![image](https://user-images.githubusercontent.com/23286838/227817667-9ab86a77-828a-4f0e-9766-be75e53f3289.png)  
> > 3. __Shuffling a linked list.__ : Given a singly-linked list containing $n$ items, rearrange the items uniformly at random. Your algorithm should consume a logarithmic (or constant) amount of extra memory and run in time proportional to $nlogn$ in the worst case.  
> > -> $n$개의 요소를 포함하는 싱글 링크드 리스트가 주어졌을 때, 항목을 무작위로 균일하게 재배열하시오. 알고리즘은 로그 혹은 상수의 추가 메모리를 사용하고 최악의 케이스에 $nlogn$의 시간 동안 실행되어야 합니다.  
> > + 제가 쓴 정답  
> > Similar to MergeSort. We need to merge two uniformly shuffled linked list, list1 of size n1 and list2 of size n2. Combine that lists into a new list of size n1 + n2.  
> > + 실제 정답  
> > ![image](https://user-images.githubusercontent.com/23286838/227817690-56517d10-48ed-4994-8e64-deaaba2d6f65.png)  
> #### 2. QuickSort  
> > 1. __Nuts and bolts.__ : A disorganized carpenter has a mixed pile of $n$ nuts and $n$ bolts. The goal is to find the corresponding pairs of nuts and bolts. Each nut fits exactly one bolt and each bolt fits exactly one nut. By fitting a nut and bolt together, the carpenter can see which one is bigger (but the capenter cannot compare two nuts and bolts directly). Design an algorithm for the problem that uses at most proportional to $nlogn$ compares(probabilistically).  
> > -> 너츠와 볼트 : 목수는 n개의 너트와 n개의 볼트가 혼합된 더미를 가지고 있다. 목표는 해당 너트와 볼트 쌍을 찾는 것이다. 각 너트는 하나의 볼트와만 일치하고 각 볼트는 하나의 너트와만 일치한다. 너트와 볼트를 맞춤으로써, 목수는 어떤 것이 더 큰지 알 수 있다.(그러나 목수는 두 개의 너트와 볼트를 한 번에 비교할 수는 없다.) 이 문제에 대한 알고리즘을 고안하시오. (확률적으로 $nlogn$ 에 가장 비례하는)  
> > + 제가 쓴 정답  
> > Use quicksort.  
> > + 실제 정답  
> > ![image](https://user-images.githubusercontent.com/23286838/227836678-d287aff8-fb1d-495a-99ac-6bf44537f34f.png)  
> > 2. __Selection in two sorted arrays.__ : Given two sorted arrays $a[]$ and $b[]$, of lengths $n_1$ and $n_2$ and an integer $0 \leq k < n_1 + n_2$, design an algorithm to find a key of rank $k$. The order of growth of the worst case running time of your algorithm should be $logn$ where $n = n_1 + n_2$.  
> > ![image](https://user-images.githubusercontent.com/23286838/227833278-ee4302ad-0e0f-4ac2-ae5f-20b3a79c2419.png)  
> > + 제가 쓴 정답  
> > ```
> > while not found target:
> >   int i = length of a/2
> >   int j = length of b/2
> >   if k > i+j then
> >     if b[j] <= a[i] then
> >       k -= j
> >       b = b[j+1:]
> >     else then
> >       k -= i
> >       a = a[i+1:]
> >   else then
> >     if b[j] <= a[j] then
> >       a = a[:i]
> >     else then
> >       b = b[:j]
> > ```
> > + 실제 정답  
> > ![image](https://user-images.githubusercontent.com/23286838/227836633-f042db31-4cf9-430b-8d17-137d2a462663.png)  
> > 3. __Demical dominants.__ : Given an array with $n$ keys, design an algorithm to find all values that occur more than $n/10$ times. The expected running time of your algorithm should be linear.  
> > -> n개의 키와 배열이 주어졌을 때, $n/10$회 이상 발생하는 모든 값을 찾는 알고리즘을 고안하라. 알고리즘의 예상 시간은 선형 시간이어야 한다.  
> > + 제가 쓴 정답  
> > Find the n/10th largest key with QuickSelect.  
> > + 실제 정답  
> > ![image](https://user-images.githubusercontent.com/23286838/227836730-90611ede-6f21-42db-8319-7415d875c7ed.png)
