# [❗️ 리팩토링 중입니다... ❗]️️
# 🧩 ImageGhostServer Web Backend

## 📃 Project Intro
```
📚 What
   클라이언트를 인증할 수 있는 기능을 제공합니다. 

👻 Why
   컴퓨터 보안 강의과 블록체인을 수강하면서 배운 내용을 응용할 수 있는 프로젝트를 고민했습니다. 
   암복호화 방식에서 흥미를 느꼈기 때문에 암복호화 응용이 가능한 프로젝트를 선정했습니다. 
   개인 프로젝트를 진행하면서 배운 내용을 직접 구현하려 했고 PGP Structure를 직접 구현했습니다. 
   타인에게 신원을 노출하지 않고 다른 사람들과 자유롭게 소통하고 공유할 수 있는 프로젝트를 선정하여 진행했습니다. 

🚕 How
   배웠던 내용을 기반으로 직접 코드로 구현하고자 했습니다. 
   PGP Structure는 배웠던 원리를 기반으로 직접 구현했습니다. 
```

## 👨‍💻 Tech
```
📕 Hash
   고유한 값을 도출하기에 무결성을 검증하는데 유용하게 사용될 수 있음. 
   
📗 AES
   암복호화 과정에서 동일한 키를 사용하므로 빠른 속도를 보장함. 
   
📘 RSA
   공개키 암호화에 사용될 수 있고 오로지 자신만 복호화할 수 있는 비밀키를 제공함. 
 
📙 PGP Structure
   안전하게 공통의 키를 나눠가질 수 있는 방식으로 Direct Message 구현 시 유용하게 쓰임. 
```


## ✔️Concept
```
🔪 공개키 암복호화
   PGP 구조를 구현하기 위해 RSA를 활용한 공개키 암복호화를 제공. 
     
💉 대칭키 암복호화
   PGP 구조를 활용해 얻어낸 대칭키를 사용해 대칭키 암복호화 기능을 제공. 
     
🔬 PGP 구조 구현
   인터넷 상에서 대칭키를 노출시키지 않고 안전하게 교환할 수 있는 기능을 제공. 
     
✏ 이미지 암복호화
   이미지를 대칭키로 암호화하여 전달하고 복호화할 수 있는 기능을 제공. 
```


## 📈Progress
```
❌ Mysql 연동  
❌ Docker 적용 
❌ Redis 적용 
❌ SSL/TLS 적용 
❌ GCP or AWS 사용  
✔️ 테스트 코드 작성하기 
❌ thymeleaf view 단 완성하기  
✔️ Logger 사용하기  
```

## ⚠️Improvements
```
😇 테스트 코드 작성하기 
😇 뷰 완성하기 - Thymeleaf 
```

## 🔗 Reference
1️⃣  Encryption & Decryption 
- https://this-programmer.tistory.com/259

2️⃣  PGP Structure 
- https://guide.jinbo.net/digital-security/communication-security/introduction-public-key-encryption 

3️⃣  Digital Signature 
- https://www.geeksforgeeks.org/java-implementation-of-digital-signatures-in-cryptography/

## 🏠 Desktop Repository
> [데스크탑 리포지토리](https://github.com/yuny0623/Java-FileShare-Desktop)
