# Holo Backend 启动指南

## 通过Docker启动应用

### 1. 下载最新Docker镜像

从package页面下载最新的docker镜像：

```bash
docker pull ghcr.io/qiqd/holo-backend:latest
```

### 2. 配置环境变量

启动前需要配置以下环境变量：

- `HOLO_DATABASE_SCHEMA=holo_db`
- `HOLO_DATABASE_URL=mongodb://your mongodb url`
- `HOLO_JWT_EXPIRATION=20`
- `HOLO_JWT_SECRET=your secret`

### 3. 启动容器

使用以下命令启动容器并配置环境变量：

```bash
docker run -d \
  --name holo-backend \
  -p 56932:56932 \
  -e HOLO_DATABASE_SCHEMA=holo_db \
  -e HOLO_DATABASE_URL=mongodb://your mongodb url \
  -e HOLO_JWT_EXPIRATION=20 \
  -e HOLO_JWT_SECRET=your secret \
  ghcr.io/qiqd/holo-backend:latest
```

### 4. 验证应用启动

启动后，可以通过以下方式验证应用是否正常运行：

- 访问 `http://localhost:56932/` 检查服务状态
- 查看容器日志：`docker logs holo-backend`

### 5. 停止应用

如需停止应用，可使用以下命令：

```bash
docker stop holo-backend
```

## 通过JAR包启动（可选）

如果你选择不使用Docker，也可以直接运行JAR包：

```bash
java -jar holo-backend.jar \
  --spring.data.mongodb.uri=mongodb://your mongodb url \
  --spring.data.mongodb.database=holo_db \
  --jwt.expiration=20 \
  --jwt.secret=your secret
```

## 环境变量说明

- `HOLO_DATABASE_SCHEMA`: MongoDB数据库名称，默认为`holo_db`
- `HOLO_DATABASE_URL`: MongoDB连接URL
- `HOLO_JWT_EXPIRATION`: JWT令牌过期时间（天），默认为30天
- `HOLO_JWT_SECRET`: JWT加密密钥

## 注意事项

1. 确保MongoDB数据库可访问
2. 端口56932需要可用
3. 环境变量中的数据库URL请根据实际环境进行调整
