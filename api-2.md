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

