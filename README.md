# 미션: 문자열 덧셈 계산기

## 시스템 이름
- 사용자가 입력한 올바른 문자열에서 숫자를 추출하여 더하는 계산기를 구현한다.

## 기능 목록

- [x] 문자열을 입력
- [x] 구분자 검증 추출
- [x] 문자열을 양수로 변환하라
- [x] 덧셈 계산
- [x] 출력

## 도메인 객체 선별

- InputView
- Delimiter
- Calculator
- Converter
- OutputView

## 공용 인터페이스

- String getString() 
- List<String> separateString(String string)
- long sum(List<Integer> numbers)
- List<Integer> convertPositiveNumbers(List<String> payLoad)
- void printResult(long sum)
 
## 가정 & 정의

- 입력 당 커스텀 구분자 입력은 한가지로 가정한다.
- 개별 입력 값은 0~int 상한 값이라 가정한다.