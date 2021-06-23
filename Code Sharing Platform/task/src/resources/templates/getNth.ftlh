<html lang="en">
<head>
    <title>Latest</title>
</head>
<style>
    #code_snippet {
        background-color: bisque;
        color: darkgreen;
    }

    #code_snippet {
        background-color: aquamarine;
        color: fuchsia;
    }
</style>
<body>
<#list code_ as code>
    <span id="load_date">${code.date}</span>
    <pre id="code_snippet">${code.code}</pre>
</#list>
</body>
</html>