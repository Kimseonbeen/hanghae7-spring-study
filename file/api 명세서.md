---
description: 선택한 게시글을 조회 한다.
---

# 선택한 게시글 조회 API



## Select board

<mark style="color:green;">`GET`</mark> `/board/{board_id}`

**Headers**

| Name         | Value              |
| ------------ | ------------------ |
| Content-Type | `application/json` |

**Body**

| Name       | Type | Description |
| ---------- | ---- | ----------- |
| `board_id` | long | 게시글 번호      |

**Response**

{% tabs %}
{% tab title="200" %}
```json
{
    "board_id": 1,
    "title": "title1",
    "content": "content1"
    "created_at": 2024-11-06
    "updated_at": 2024-11-06
}
```
{% endtab %}

{% tab title="400" %}
```json
{
  "error": "Invalid request"
}
```
{% endtab %}
{% endtabs %}

# 게시글 작성 API

## Create board

<mark style="color:green;">`POST`</mark> `/board`

**Headers**

| Name         | Value              |
| ------------ | ------------------ |
| Content-Type | `application/json` |

**Body**

| Name      | Type   | Description |
| --------- | ------ | ----------- |
| `title`   | string | 게시글 제목      |
| `content` | string | 내용          |

**Response**

{% tabs %}
{% tab title="200" %}
```json
{
    "board_id": 3,
    "title": "title3",
    "content": "content3"
    "created_at": 2024-11-06
    "updated_at": 2024-11-06
}
```
{% endtab %}

{% tab title="400" %}
```json
{
  "error": "Invalid request"
}
```
{% endtab %}
{% endtabs %}

---
description: 선택한 게시글을 수정한다.
---

# 선택한 게시글 수정 API

## Update board

<mark style="color:green;">`FETCH`</mark> `/board/{board_id}`

**Headers**

| Name         | Value              |
| ------------ | ------------------ |
| Content-Type | `application/json` |

**Body**

| Name       | Type   | Description |
| ---------- | ------ | ----------- |
| `board_id` | string | 게시글 아이디     |
| `password` | string | 비밀번호        |

**Response**

{% tabs %}
{% tab title="200" %}
```json
{
  "board_id": 3,
  "title": "update_title3",
  "content": "update_content3"
  "created_at": 2024-11-06
  "updated_at": 2024-11-06
}
```
{% endtab %}

{% tab title="400" %}
```json
{
  "error": "Invalid request"
}
```
{% endtab %}
{% endtabs %}

# 선택한 게시글 삭제 API

## Delete board

<mark style="color:green;">`DELETE`</mark> `/board/{board_id}`

**Headers**

| Name         | Value              |
| ------------ | ------------------ |
| Content-Type | `application/json` |

**Body**

| Name       | Type   | Description |
| ---------- | ------ | ----------- |
| `board_id` | string | 게시글 아이디     |
| `password` | string | 비밀번호        |

**Response**

{% tabs %}
{% tab title="200" %}
```json
{
  "status": "success",
  "message": "게시글이 성공적으로 삭제 되었습니다."
}
```
{% endtab %}

{% tab title="400" %}
```json
{
  "error": "Invalid request"
}
```
{% endtab %}
{% endtabs %}
