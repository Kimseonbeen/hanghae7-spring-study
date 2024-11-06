---
description: 전체 게시글 목록을 조회한다.
---

# 전체 게시글 목록 조회 API



## Select boards

<mark style="color:green;">`GET`</mark> `/boards`

**Headers**

| Name         | Value              |
| ------------ | ------------------ |
| Content-Type | `application/json` |

**Response**

{% tabs %}
{% tab title="200" %}
```json
[
  {
    "board_id": 1,
    "title": "title1",
    "content": "content1"
    "created_at": 2024-11-06
    "updated_at": 2024-11-06
  }
  {
    "board_id": 2,
    "title": "title2",
    "content": "content2"
    "created_at": 2024-11-06
    "updated_at": 2024-11-06
  }
]
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
