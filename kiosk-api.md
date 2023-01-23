---
description: 항해 11기 5팀
---

# Kiosk API

## 회원 가입

{% swagger method="post" path="/member/join)" baseUrl="(" summary="폰번호를 참조해서 규칙에 맞을 경우 회원 가입 승인" %}
{% swagger-description %}

{% endswagger-description %}

{% swagger-parameter in="query" name="id" type="bigint" required="true" %}
pk
{% endswagger-parameter %}

{% swagger-parameter in="query" name="created_at" type="varchar" required="true" %}
회원 가입된 시간
{% endswagger-parameter %}

{% swagger-parameter in="query" name="phone_number" type="varchar" required="true" %}
폰 번호
{% endswagger-parameter %}

{% swagger-parameter in="query" name="point" type="integer" required="true" %}
포인트
{% endswagger-parameter %}

{% swagger-parameter in="query" name="role" type="varchar" required="true" %}
role은 멤버로 부여한다
{% endswagger-parameter %}

{% swagger-parameter in="query" name="sms_agreement" type="boolean" required="true" %}
문자 수신 동의 여부
{% endswagger-parameter %}

{% swagger-response status="200: OK" description="승인" %}
```javascript
{
    // Response
}
```
{% endswagger-response %}

{% swagger-response status="400: Bad Request" description="번호는 11자리여야 합니다." %}
```javascript
{
    // Response
}
```
{% endswagger-response %}

{% swagger-response status="400: Bad Request" description="이미 등록된 번호입니다." %}
```javascript
{
    // Response
}
```
{% endswagger-response %}
{% endswagger %}

## 로그인

{% swagger method="post" path="/member/login)" baseUrl="(" summary="실제로 로그인을 하지는 않는다." %}
{% swagger-description %}

{% endswagger-description %}

{% swagger-parameter in="query" name="id" type="bigint" required="true" %}
pk
{% endswagger-parameter %}

{% swagger-parameter in="query" name="phone_number" type="varchar" required="true" %}
폰번호
{% endswagger-parameter %}

{% swagger-response status="200: OK" description="확인" %}
```javascript
{
    // Response
}
```
{% endswagger-response %}

{% swagger-response status="400: Bad Request" description="번호는 11자리여야 합니다." %}
```javascript
{
    // Response
}
```
{% endswagger-response %}

{% swagger-response status="400: Bad Request" description="등록된 회원이 아닙니다." %}
```javascript
{
    // Response
}
```
{% endswagger-response %}
{% endswagger %}

##

## 메뉴 저장

{% swagger method="post" path="/store/menus)" baseUrl="(" summary="원하는 메뉴를 저장한다." %}
{% swagger-description %}

{% endswagger-description %}

{% swagger-parameter in="query" name="id" required="true" type="bigint" %}
pk
{% endswagger-parameter %}

{% swagger-parameter in="query" required="true" name="image_url" type="varchar" %}
원하는 이미지 삽입
{% endswagger-parameter %}

{% swagger-parameter in="query" required="true" name="menu_name" type="varchar" %}
메뉴 이름
{% endswagger-parameter %}

{% swagger-parameter in="query" required="true" name="price" type="integer" %}
메뉴의 가격
{% endswagger-parameter %}

{% swagger-response status="200: OK" description="" %}
```javascript
{
    // Response
}
```
{% endswagger-response %}

{% swagger-response status="500: Internal Server Error" description="" %}
```javascript
{
    // Response
}
```
{% endswagger-response %}
{% endswagger %}

## 메뉴 조회

{% swagger method="get" path="/store/menus)" baseUrl="(" summary="메뉴의 가시화" %}
{% swagger-description %}

{% endswagger-description %}

{% swagger-parameter in="query" name="id" type="bigint" required="true" %}
pk
{% endswagger-parameter %}

{% swagger-parameter in="query" name="image_url" type="varchar" required="true" %}
원하는 이미지 삽입
{% endswagger-parameter %}

{% swagger-parameter in="query" name="menu_name" type="varchar" required="true" %}
메뉴 이름
{% endswagger-parameter %}

{% swagger-parameter in="query" name="price" type="integer" required="true" %}
메뉴의 가격
{% endswagger-parameter %}
{% endswagger %}

## 메뉴 삭제

{% swagger method="delete" path="/store/menus/{id})" baseUrl="(" summary="id를 매칭해서 맞다면 삭제" %}
{% swagger-description %}

{% endswagger-description %}

{% swagger-parameter in="query" name="id" type="bigint" required="true" %}

{% endswagger-parameter %}

{% swagger-response status="200: OK" description="" %}
```javascript
{
    // Response
}
```
{% endswagger-response %}

{% swagger-response status="500: Internal Server Error" description="" %}
```javascript
{
    // Response
}
```
{% endswagger-response %}
{% endswagger %}

##

## 주문 접수

{% swagger method="post" path="/order)" baseUrl="(" summary="내역을 바탕으로 주문 정보를 저장한다." %}
{% swagger-description %}

{% endswagger-description %}

{% swagger-parameter in="query" name="id" type="bigint" required="true" %}
pk
{% endswagger-parameter %}

{% swagger-parameter in="query" name="created_at" type="varchar" required="true" %}
접수 시간
{% endswagger-parameter %}

{% swagger-parameter in="query" name="amount" type="integer" required="true" %}
접수 수량
{% endswagger-parameter %}

{% swagger-parameter in="query" name="image_url" type="varchar" required="true" %}
메뉴 이미지
{% endswagger-parameter %}

{% swagger-parameter in="query" name="menu_name" type="varchar" required="true" %}
메뉴 이름
{% endswagger-parameter %}

{% swagger-parameter in="query" name="menu_id" type="bigint" required="true" %}
참조값
{% endswagger-parameter %}

{% swagger-parameter in="header" name="Authorization" required="true" %}
bearer ey~~~~~~
{% endswagger-parameter %}

{% swagger-response status="200: OK" description="" %}
```javascript
{
    // Response
}
```
{% endswagger-response %}

{% swagger-response status="400: Bad Request" description="" %}
```javascript
{
    // Response
}
```
{% endswagger-response %}

{% swagger-response status="405: Method Not Allowed" description="" %}
```javascript
{
    // Response
}
```
{% endswagger-response %}
{% endswagger %}

## 주문 내역

{% swagger method="get" path="/order/bill)" baseUrl="(" summary="영수증(총합)" %}
{% swagger-description %}
토큰을 토대로 주문 정보를 조회 후 수량과 값을 참조해 총합을 낸다
{% endswagger-description %}

{% swagger-parameter in="query" name="amount" type="integer" required="true" %}
수량
{% endswagger-parameter %}

{% swagger-parameter in="query" name="price" type="integer" required="true" %}
메뉴 가격
{% endswagger-parameter %}

{% swagger-parameter in="header" name="Authorization" required="true" %}
bearer ey~~~~~~
{% endswagger-parameter %}

{% swagger-response status="200: OK" description="" %}
```javascript
{
    // Response
}
```
{% endswagger-response %}

{% swagger-response status="400: Bad Request" description="" %}
```javascript
{
    // Response
}
```
{% endswagger-response %}

{% swagger-response status="405: Method Not Allowed" description="" %}
```javascript
{
    // Response
}
```
{% endswagger-response %}
{% endswagger %}

## 포인트 적립

{% swagger method="post" path="/order/points)" baseUrl="(" summary="영수증을 기준으로 포인트 적립" %}
{% swagger-description %}

{% endswagger-description %}

{% swagger-parameter in="query" name="phoneNumber" type="varchar" required="true" %}
폰번호
{% endswagger-parameter %}

{% swagger-parameter in="query" name="point" type="integer" required="true" %}
포인트
{% endswagger-parameter %}

{% swagger-parameter in="query" name="amount" type="integer" %}
주문수량
{% endswagger-parameter %}

{% swagger-parameter in="query" name="price" type="integer" %}
메뉴 가격
{% endswagger-parameter %}

{% swagger-parameter in="header" name="Authorization" required="true" %}
bearer ey~~~~~~
{% endswagger-parameter %}

{% swagger-response status="200: OK" description="" %}
```javascript
{
    // Response
}
```
{% endswagger-response %}

{% swagger-response status="405: Method Not Allowed" description="" %}
```javascript
{
    // Response
}
```
{% endswagger-response %}
{% endswagger %}

## 일 매출

{% swagger method="post" path="/order/dailySales)" baseUrl="(" summary="date를 기준으로 매출을 낸다." %}
{% swagger-description %}

{% endswagger-description %}

{% swagger-parameter in="query" required="true" name="created_at" type="varchar" %}
주문 시간
{% endswagger-parameter %}

{% swagger-parameter in="query" required="true" name="amount" type="integer" %}
수량
{% endswagger-parameter %}

{% swagger-parameter in="query" required="true" name="price" type="integer" %}
가격
{% endswagger-parameter %}

{% swagger-parameter in="query" required="true" type="varchar" name="menu_name" %}
메뉴 이름
{% endswagger-parameter %}

{% swagger-response status="200: OK" description="" %}
```javascript
{
    // Response
}
```
{% endswagger-response %}
{% endswagger %}

##

## 주문 번호

{% swagger method="post" path="/order/orderNumber)" baseUrl="(" summary="주문 번호" %}
{% swagger-description %}

{% endswagger-description %}

{% swagger-parameter in="query" name="id" type="bigint" required="true" %}
pk
{% endswagger-parameter %}

{% swagger-parameter in="query" name="created_at" type="varchar" required="true" %}
주문 시간
{% endswagger-parameter %}

{% swagger-parameter in="query" name="order_cnt" type="bigint" required="true" %}
주문 합계
{% endswagger-parameter %}

{% swagger-response status="200: OK" description="" %}
```javascript
{
    // Response
}
```
{% endswagger-response %}
{% endswagger %}

##
