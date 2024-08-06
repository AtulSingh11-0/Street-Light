Sure, here's a markdown representation of the provided API endpoints:

```markdown
# Street Light Detection API Endpoints

## Area

### GET All Areas
```
GET http://localhost:3001/gov/original/area/all
```
### PUT Update by Area Code
```
PUT http://localhost:3001/gov/original/area/update/12
```
**Body (raw JSON):**
```json
{
    "areaName": "New Garia",
    "areaCode": "123"
}
```

## Transformer

### GET by Transformer Code
```
GET http://localhost:3001/gov/original/area/transformer/9abc
```
### PUT Update by Transformer Code
```
PUT http://localhost:3001/gov/original/area/transformer/update/9abc
```
**Body (raw JSON):**
```json
{
    "transformerName": "Trans9",
    "transformerCode": "9abc",
    "areaModel": {
        "id": 3,
        "areaName": "Howrah",
        "areaCode": "789"
    }
}
```

## Street Light

### GET All Street Lights
```
GET http://localhost:3001/gov/original/area/transformer/street-light/all
```
### GET by Street Light Code
```
GET http://localhost:3001/gov/original/area/transformer/street-light/27xy
```

```

This markdown file outlines the various endpoints for managing areas, transformers, and street lights in the street light detection system. Let me know if you need further assistance!