---
description: 선택한 게시글을 수정한다.
---

# 선택한 게시글 수정 API

## Update board

<mark style="color:green;">`FETCH`</mark> `/board/{board_id}`

\<Description of the endpoint>

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

