### 기능
1. 사용자 실시간 위치 기반 긴급 서비스 시설 위치 제공 : 백엔드
2. 회원가입 기능
3. 사용자 간의 위치 공유 기능
4. 재난 문자 파싱 기능

## 세부 개발
### 1. 실시간 위치 기반 긴급 서비스 시설 위치 제공
1. 사용자의 실시간 위치 기능을 제공하는 API : 진행중
2. 대구 대피소 DB 구축 : 완료
3. 길찾기 API : 사용중
4. 긴급 서비스 시설 위치 DB 구축 : 완료(경찰서, 병원, 소방서, 119센터)
5. 일정 시간 간격으로 클라이언트에 저장 기능 : 예정

### 2. 회원가입 기능
1. JWT 토큰 사용해서 해보기 : 예정

### 3. 사용자 간의 위치 공유 기능
1. POST : 클라이언트1 -> 백엔드 : 다른 사용자 위치 권한 허용 요청
2. 응답 : 백엔드 -> 클라이언트2 : 권한 허용 선택
3. POST : 클라이언트2 -> 백엔드 : 허용 선택
4. 응답 : 백엔드 -> 클라이언트1 : 권한 허용

### 4. 재난 문자 파싱 기능
1. 재난 문자 API : 예정
2. 재난 문자 가공 API 찾기 : 예정

### 도메인 개발
1. Member 친구 추가 기능.
2. 최근 위치 데이터 저장
3. select join 기능

#### api 테스트
1. [CHAT GPT]: 정상 동작 / https://api.openai.com/v1/chat/completions?serviceKey=xxx
2. [119안전센터]: 정상 동작 / localhost:8080/api/safecenter119 
3. [병원]: 정상 동작 / http://apis.data.go.kr/B552657/HsptlAsembySearchService/getHsptlMdcncListInfoInqire?serviceKey=xxx&numOfRows=2000&QZ=B
4. [소방서]: 정상 동작 / localhost:8080/api/firestation
5. [경찰서]: 정상 동작 / localhost:8080/api/police