<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="img/favicon.ico">

    <title>Like page</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css"
          integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
    <!-- Bootstrap core CSS -->
    <link href="../static/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="../static/css/style.css">
</head>
<body style="background-color: #f5f5f5;">

<div class="col-4 offset-4">
    <a href="/products" class="badge-secondary">All products</a>
    <#if id??>
    <form action="/products/add/${id}" method="post">
        <div class="form-group">
            <label for="name">Name:</label>
            <input type="text" name="name" class="form-control" value="${name}" id="name" required>
        </div>
        <div class="form-group">
            <label for="price">Price:</label>
            <input type="number" name="price" class="form-control" value="${price}" id="price" required>
        </div>

        <button type="submit" class="btn btn-default">Submit</button>
    </form>
    <#else>
        <form action="/products/add" method="post">
            <div class="form-group">
                <label for="name">Name:</label>
                <input type="text" name="name" class="form-control" id="name" required>
            </div>
            <div class="form-group">
                <label for="price">Price:</label>
                <input type="number" name="price" class="form-control" id="price" required>
            </div>
            <button type="submit" class="btn btn-default">Submit</button>
        </form>
    </#if>
</div>

</body>
</html>
