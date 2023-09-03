# STL

  

```c++
Struct

bool operator < (Student &student){
	return this->score < student.score;
}


```



## std :: vector  -dynamic array

- 메모리상에서 데이터가 연속적으로 위치하는 배열

- std::vector의 경우 배열의 크기를 런타임에서 조절할 수 있지만 std::array의 크기는 컴파일 타임에 결정되어야 하고 런타임에서 바꿀 수 없습니다

  

- std::vector는 포인터 세 개로 구현되어 있습니다.

  - 할당된 배열의 시작 주소를 가리키는 포인터
  - 다음에 데이터가 삽입될 위치를 가리키는 포인터
  - 할당된 배열의 끝 주소를 가리키는 포인터

```c++
# vector  기본 


v.assign(n, m)	//m으로 n개의 원소 할당
v.at(index)	//index번째 원소를 반환한다. 유효한 index인지 체크하기 때문에 안전하다.
v[index]	//index번째 원소를 반환한다. 배열과 같은 방식이며 유효성을 체크하지 않는다.
v.front()	//첫 번째 원소를 반환한다.
v.back()	//마지막 원소를 반환한다.
v.clear()	//모든 원소를 제거한다. 메모리는 그대로 남아있게 된다. (size는 줄어들고 capacity는 유지)
v.push_back(m)	//마지막 원소 뒤에 원소 m을 삽입한다.
v.pop_back()	//마지막 원소를 제거한다.


vector.capacity()  // 공간 수 
vector.reserve(num)  // num 만큼 용량을 늘림

    
    
    
#vector iterator
    
vector.begin()  //vector의 첫 번째 요소를 가리키는 반복자 반환 
vector.end()  //vector의 마지막 요소의 다음 요소를 가리키는 반복자 반환 
vector.rbegin() //vector의 마지막 요소를 가리키는 역방향 반복자를 반환
vector.rend()  //vector의 첫 번째 요소의 앞 요소를 가리키는 역방향 반복자를 반환
    
v.resize(n)	//크기를 n개로 변경한다. 커진 경우에는 빈 곳을 0으로 초기화한다.
v.resize(n, m)	//크기를 n개로 변경한다. 커진 경우에는 빈 곳을 m으로 초기화한다.
v.size()	//원소의 개수를 반환한다.

v2.swap(v1)	//v1과 v2를 swap한다.
v.insert(iter, m)	//iter가 가리키는 위치에 m의 값을 삽입한다. 그리고 해당 위치를 가리키는 반복자(iterator)를 반환
v.insert(iter, k, m)	//iter가 가리키는 위치부터 k개의 m 값을 삽입한다. 다음 원소들은 뒤로 밀린다.
v.erase(iter)	//iter 반복자가 가리키는 원소를 제거한다. capacity는 그대로 유지된다.
v.erase(start, end)	//start 반복자부터 end 반복자까지 원소를 제거한다.
v.empty()	//vector가 비어있으면 true를 반환한다.
v.max_size()	//v가 담을 수 있는 최대 원소의 개수(메모리 크기) 반환
v.shrink_to_fit()	//capacity의 크기를 vector의 실제 크기에 맞춤
    
    
#vetor sort
    
sort(vec.begin(), vec.end());  //오름차순 
sort(vec.begin(), vec.end(), greater<int>()); //내림차순



#vector 탐색

find(v.begin(), v.end(), 3)  
//vector에서 요소의 값이 3인 요소 위치의 반복자 반환
//vector의 범위에 찾을 대상이 없는 경우 마지막 반복자인 v.end()를 반환
```

```c++
bool sorting(pair <int,int> p, pair <int,int> p2) { // compare 함수 sort에서 사용
    if (p.first == p2.first) { // x 좌표가 같다면
        return p.second < p2.second; // y 좌표를 오름차순으로
    }
    
    return p.first < p2.first; // x 좌표가 같지 않다면 x 좌표를 오름차순으로
}
 //sort(dv 배열의 시작점,dv 배열의 시작점 + 10,greater 사용(내림차순 정렬))
 sort(dv,dv+10,greater<double>());
```



## std :: deque -앞, 뒤에 데이터를 빠르게 넣고 뺄 수 있는 double-ended queue

- std::deque는 여러 개의 버퍼에 데이터를 나눠서 저장합니다. s
- std::deque는 버퍼 하나만 할당하면 되므로, 데이터 삽입이 언제든 O(1)입니다

```c++
dq.begin() : dq의 첫 번째 원소를 가리키는 iterator
    
dq.end() : dq의 마지막 원소를 가리키는 iterator
    
dq.front() : dq의 첫 번째 원소
    
dq.back() : dq의 마지막 원소
dq.at(n) : dq의 n번째 원소
dq.assign(n) : 원소 n개를 0으로 초기화
dq.assign(n,1) : 원소 n개를 1로 초기화
    
dq.push_front(x) : dq의 첫 번째 원소에 'x' 추가
dq.push_back(x) : dq의 마지막 원소에 'x' 추가
dq.pop_front() : dq의 첫 번째 원소 삭제
dq.pop_back() : dq의 마지막 원소 삭제
    
dq.size() : dq의 원소 개수 리턴
dq.resize(m) : dq의 메모리 공간 크기를 m으로 변경하고 늘어난 부분은 0으로 초기화
dq.clear() : dq 전체 원소 clear!
    
dq.insert(iterator, x) : iterator가 가리키는 위치에 원소 'x' 삽입
(기존 원소들은 한 칸씩 뒤로 밀려난다.)
dq.erase(iterator) : iterator가 가리키는 위치의 dq원소를 삭제
```



## std:: list

- linked list입니다. Container의 어느 위치든 O(1)에 데이터를 삽입하거나 삭제할 수 있지만 random access(Container의 i번째 데이터에 O(1)에 접근)는 불가능합니다.

- std::list는 doubly-linked list이고, std::forward_list는 singly-linked list입니다.  
- singly-linked list는 몇 가지 기능이 제한됩니다. Container 맨 앞에 데이터를 삽입하는 건 빠르지만 맨 뒤에 삽입하는 건 느립니다. 어떤 iterator의 다음 iterator는 삭제할 수 있지만 그 iterator 자신은 삭제하지 못합니다. 
- 하지만 doubly-linked list에 비해 포인터를 하나 덜 가지므로 기본적인 연산이 빠르고 메모리를 적게 사용합니다. 만약 여러분이 doubly-linked list의 기능까지 필요하지 않다면 더 가볍고 빠른 std::forward_list가 좋은 선택일 것입니다.



# **Associative Containers**

-데이터를 정렬된 상태로 유지하는 자료구조입니다. **Red-Black Tree**를 기반으로 하고 데이터의 추가/삭제/접근의 시간복잡도가 O(logn)입니다. 데이터를 정렬된 상태로 유지하는 것은 매우 강력한 기능이고 O(logn)은 적은 시간복잡도지만 연산에 붙는 상수가 크고 사용하는 메모리가 많으므로 주의가 필요합니다.

**Red-Black Tree: -** 자가 균형 이진 탐색 트리(self-balancing binary search tree)로서, 대표적으로는 연관 배열 등을 구현하는 데 쓰이는 자료구조입니다. 트리에 n개의 원소가 있을 때 O(log *n*)의 시간복잡도로 삽입, 삭제, 검색을 할 수 있으며 최악의 경우에도 우수한 실행 시간을 가집니다.



## std::set (std::map) 

- Red-Black Tree는 Binary Search Tree이므로 어떤 key를 기준으로 데이터를 저장합니다. std::set은 데이터를 자체를 key로 사용하고, std::map은 (key, value) 쌍을 받아서 사용합니다.  

- 단순히 데이터를 정렬 상태로 유지하고 싶다면 std::set을, (key, value) 데이터 쌍을 key를 기준으로 정렬하고 싶다면 std::map을 사용하면 됩니다.

```c++
map.begin()

map.end()

map.rbegin() - end()랑 같음

map.rend() - begin()이랑 같음

map.clear() - 모든 원소 제거

map.count(key) - key값에 해당하는 원소들의 개수 반환

map.empty() - 비어있으면 true, 아니면 false 반환

map.insert(make_pair(key, value)) - 원소를 pair 형태로 삽입

map["key"] = 5;  // 이렇게 삽입하는것도 가능

map.insert(iter, k) - iter가 가리키는 위치부터 k를 삽입할 위치를 탐색하여 삽입

map.erase(iter) - iter가 가리키는 원소 제거

map.erase(key) - key에 해당하는 원소 제거

map.erase(start, end)

map.find(key) - key를 가리키는 iterator 반환. 없으면 map.end()의 iterator 반환
```

```
Set 함수

s.begin()
s.end()
s.rbegin()
s.rend()

s.clear()
s.empty()
s.size()
s.count(k) //소 k의 개수 반환  // 무조건 0 아니면 1 -> multiset에서는 유용하게 쓰임

s.insert(k)  //자동으로 정렬된 위치에 원소 k를 삽입

s.insert(iter, k)  //iter가 가리키는 위치에 k 삽입

s.erase(iter)// iter가 가리키는 위치의 원소 삭제

s.erase(start, end)  //[start, end) 구간의 원소 삭제

s.find(k) //원소 k가 가리키는 iter 반환 //k가 없으면 s.end()의 iter 반환

s2.swap(s1) //s2와 s1을 완전히 바꿔줌

s.upper_bound(k) //원소 k가 끝나는 구간의 반복자를 반환

s.lower_bound(k) //원소 k가 시작하는 구간의 반복자를 반환

s.equal_range(k)  원소 k가 시작하는 구간과 끝나는 구간의 반복자를 반환//upper + lower
```



## std::multiset (std::multimap) 

- 원소의 중복을 허용합니다. 같은 key를 여러 개 저장하고 싶을 때 사용합니다. 단, 시간복잡도 주의가 필요합니다. std::set에서 key의 개수를 세거나, key를 지우는 함수는 모두 O(logn)이지만, std::multiset에서는 같은 key를 모두 세고, 모두 지우므로 O(logn + (같은 key를 가지는 데이터의 개수)) 만큼의 시간이 듭니다.



# Unordered Associative Containers

해시값을 사용해 데이터를 저장하는 자료구조입니다. 대부분의 경우에서 데이터의 추가/삭제/접근이 O(1)이므로 Associated Container보다 효율적입니다. 하지만 데이터를 정렬된 상태로 유지해야 하거나 (무수히 많은 key 값들로 인해) 해시 충돌이 걱정되는 상황이라면 Associated Container를 사용하는 게 좋습니다.

## std::unordered_set (std::unordered_map)

- Data를 중복 없이 저장하고 싶고, Data의 순서가 상관없을 때 Associateve Container 대신 사용할 수 있습니다.



## std::unordered_multiset (std::unordered_multimap)

- Associateve Container 때와 마찬가지로, 같은 key를 가진 데이터를 중복으로 가져야 할 때 사용됩니다.





# Container Adaptors

이들은 std library에 실제로 구현되어 있지 않습니다. Sequence Container를 건네주면 그것을 자기 용도에 맞춰서 사용할 수 있도록 인터페이스만 제공합니다.



## std::priority_queue

Container를 max heap으로 유지합니다. 데이터가 완벽히 정렬된 상태는 아니지만 최댓값은 빠르게 찾을 수 있습니다. 연속적으로 데이터의 최댓값 또는 최솟값만 필요할 때는 상수가 큰 std::set보다 훨씬 효율적으로 동작합니다.

```c++
priority_queue<int> q;	// 내림차순 
priority_queue<int, vector<int>, less<int> > q;	// 내림차순  -> 큰값우선
priority_queue<int, vector<int>, greater<int> > q;	// 오름차순  -> 작은 값우선

priority_queue<자료형, 구현체, 비교연산자> pq;

empty : priorityqueue가 비어있는지 확인한다.
size : priorityqueue의 크기를 확인한다.
top : priorityqueue 내부의 제일 우선순위의 값을 보여준다.
push : priorityqueue에 값을 삽입한다.
emplace : priorityqueue에 구조를 삽입한다.
pop : priorityqueue에서 제일 우선순위의 값을 제거한다.
swap : 두개의 priorityqueue를 swap한다.(내부를 서로 바꾼다.)
    
    
push와 emplace의 차이
밑에서 더 설명하겠지만, 간단히 말하면 우선순위 큐의 경우 구조체를 많이 삽입하게 된다.

push의 경우 단순히 queue에 값을 넣어준다. 다시 말하면 오브젝트로 제작후 삽입해야하므로 불필요한 복사가 많이 일어난다.

emplace의 경우 오브젝트를 생성하지 않고 바로 값을 넣는다. 즉, copy와 constructor가 합쳐진 것이라 볼 수 있다.

아래 예시코드로 두개의 사용법과 차이점을 자세히 살펴보자.
    
#include <queue> // 우선순위 큐를 위한 라이브러리
#include <iostream> // cout사용을 위한 라이브러리
#include <utility> // pair를 위한 라이브러리
using namespace std; 
    
int main() { 
    // pair를 요소로 갖는 priority_queue를 선언
    priority_queue< pair<char, int> > pqueue; 
        
    // pair의 오브젝트를 만들지 않고 바로 값을 push한다
    pqueue.emplace('a', 24); 
        
    // 아래의 주석처리된 명령어는 오류가 난다.
    // pqueue.push('b', 25);     
        
    // push를 사용할 때에는 pair를 만들어 준 다음에 넣어야 한다. 이 과정에서 불필요한 복사가 일어난다.
    pqueue.push(make_pair('b', 25));
        
    // 우선순위 큐 출력부분
    while (!pqueue.empty()) { 
        pair p =  pqueue.top(); 
        cout << p.first << " "
             << p.second << endl; 
        pqueue.pop(); 
    } 

    // 출력 결과
    // b 25
    // a 24
    return 0; 
} 
```

```c++
#include <iostream>
#include <queue>
#include <functional>    // greater
using namespace std;
 
struct Student {
    int id;
    int math, eng;
    Student(int num, int m, int e) : id(num), math(m), eng(e) {}    // 생성자 정의
    
        // 그냥 점수에 상관 없이 학번기준 학번이 작은것이 Top 을 유지 한다
    bool operator<(const Student s) const {    //연산자 오버로딩
        return this->id > s.id;
    }
};
 
// 학번을 기준으로 학번(id) 값이 큰 것이 Top 을 유지 하도록 한다.
struct cmp {   //compare 함수
    bool operator()(Student a, Student b) {
        return a.id < b.id;
    }
};
 
int main() {
    // 위에서 만든 cmp 구조체를 넣어 준다.
    priority_queue<Student, vector<Student>, cmp> pq;  
 
 
    pq.push(Student(3, 100, 50));
    pq.push(Student(1, 60, 50));
    pq.push(Student(2, 80, 50));
    pq.push(Student(4, 90, 50));
    pq.push(Student(5, 70, 40));
    
    while (!pq.empty()) {
        Student ts = pq.top(); pq.pop();
        cout << "(학번, 수학 , 영어 ) : " << ts.id << ' ' << ts.math << ' ' << ts.eng << '\n';
    }
 
    return 0;
}

```

