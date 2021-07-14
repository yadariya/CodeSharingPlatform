<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Code</title>
    <link rel="stylesheet"
          href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
</head>
<body>
<#if timeRestriction == true>
    <span id="time_restriction" type="text">${time}</span>
    <br/>
</#if>
<#if viewRestriction == true>
    <span id="views_restriction" type="text">${view-1}</span>
    ${code.setViews(view-1)}
    ${service.save(code)}
    <br/>
</#if>
<span id="load_date"> ${code.date}</span>
<pre id="code_snippet"><code>${code.code}</code>
</pre>
</body>
</html>