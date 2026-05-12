# lenzetto-tracker

Spring Boot 4 app that checks Fass.se and Swedish online pharmacies for Lenzetto availability in Skåne every 2 hours and emails results to `pekelund@gmail.com`.

## Run

```bash
mvn spring-boot:run
```

## Email configuration

Set SMTP environment variables before running:

- `SPRING_MAIL_HOST`
- `SPRING_MAIL_PORT` (optional, default `587`)
- `SPRING_MAIL_USERNAME`
- `SPRING_MAIL_PASSWORD`

The recipient defaults to `pekelund@gmail.com` and can be overridden via:

```yaml
lenzetto:
  tracker:
    recipient: someone@example.com
```
