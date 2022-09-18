<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>统一认证中心</title>

    <#import "common/common.macro.ftl" as netCommon>
    <@netCommon.commonStyle />
</head>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">
        <!-- header -->
    <@netCommon.commonHeader />
        <!-- left -->
    <@netCommon.commonLeft "help" />

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>使用教程</h1>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="callout callout-info">
                <h4>分布式单点登录框架</h4>
            </div>
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->
</div>
<@netCommon.commonScript />
</body>
</html>
