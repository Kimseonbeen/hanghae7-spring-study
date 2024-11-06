---
description: 선택한 게시글을 조회 한다.
---

# 선택한 게시글 조회 API



## Select board

<mark style="color:green;">`GET`</mark> `/board/{board_id}`

\<Description of the endpoint>

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
