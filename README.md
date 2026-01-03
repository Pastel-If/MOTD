# MOTD Plugin
MOTD(Message Of The Day) 변경 플러그인입니다.
MOTD에 보이는 메시지를 수정 가능하며, 서버 아이콘 변경과 최대 플레이어 수도 변경이 가능합니다.

## 설치 방법
1. 버전에 맞는 플러그인을 다운로드합니다.
2. 플러그인을 `plugins` 폴더에 넣고 서버를 재시작합니다.
3. `Plugins/MOTD` 폴더에 있는 `config.yml` 파일에서 MOTD 및 서버 아이콘 설정을 합니다.

## 수정 방법
`config.yml` 파일을 수정한 후 서버 재시작 혹은 `/motd reload` 명령어를 사용해주세요.
```
# motd 기능을 사용할 지 여부 (true/false)
use-motd: true

# miniMessage 문법을 참고해주세요.
motd:
  - "<gradient:#8000ff:#ffffff><bold>⚡ MOTD 플러그인 기본 텍스트 ⚡</bold></gradient>"
  - "<white>서버에 오신 걸 환영합니다!</white>"

# server-icon 기능을 사용할 지 여부 (true/false)
use-server-icon: true

# server-icon 폴더에 png 사진을 넣어주세요. (64x64, 128x128 사이즈 추천)
server-icon-path: "server-icon.png"

# 서버 최대 플레이어 설정
max-players: 100
```

## 명령어
- `/motd reload` 설정 파일을 다시 불러옵니다.
