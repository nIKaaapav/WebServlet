<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="img/favicon.ico">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <title>People list</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.13/css/all.css" integrity="sha384-DNOHZ68U8hZfKXOrtjWvjxusGo9WQnrNx2sqG0tfsghAvtVlRW3tvkXWZh58N9jp" crossorigin="anonymous">
    <!-- Bootstrap core CSS -->
    <link href="../static/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link rel="stylesheet" href="../static/css/style.css">
</head>
<body>

<div class="container">
    <a href="/products" class="badge-secondary">All products</a>
    <a href="/products/add" class="badge-primary">Add product</a>
    <table class="table">
        <thead class="thead-dark">
        <tr>
            <th scope="col">#</th>
            <th scope="col">Name</th>
            <th scope="col">Creation date</th>
            <th scope="col">Price</th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>

        <#list products as row>
            <tr>
                <th scope="row">${row.id}</th>
                <td>${row.name}</td>
                <td>${row.creationDate}</td>
                <td>${row.price}</td>
                <td>
                    <form method="POST" action="/products">
                        <button type="submit" name="delete" value="${row.id}">delete</button>
                    </form>
                </td>
                <td>
                    <a href="/products/add/${row.id}" class="badge-secondary">Edit product</a>
                </td>
            </tr>
        </#list>
        </tbody>
    </table>
</div>

</body>
</html>
