# 선택한 게시글 삭제 API

## Delete board

<mark style="color:green;">`DELETE`</mark> `/board/{board_id}`

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

