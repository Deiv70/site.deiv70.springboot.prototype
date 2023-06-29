# ***DDD + Hexagonal (Ports & Adapters)*** **Architecture**

---

## Index

<!-- TOC -->
* [***DDD + Hexagonal (Ports & Adapters)*** **Architecture**](#ddd--hexagonal-ports--adapters-architecture)
  * [Index](#index)
  * [Layout](#layout)
<!-- TOC -->

---

## Layout

- **[3.1 infraestructure/primary][3.1]**
  - **<--*dto*--> [3.1.1. controller][3.1.1] --*([3.1.3. mapper][3.1.3])*--> _([1.1.1 application/usecase][1.1])_**
  - **<--*dto*-- [3.1.2. presenter][3.1.2] <--*([3.1.3. mapper][3.1.3])*-- _([1.1.1 application/usecase][1.1])_**
  - **[3.1.3. mapper][3.1.3] --> _([0.2 domain/model/**][0.2])_**

- **[1. application][1]**
  - **[1.1. usecase][1.1]**


[3.1]: <./infrastructure/primary> "infraestructure/primary"  

[3.1.1]: <./infrastructure/primary/controller> "infraestructure/primary/controller"  
[3.1.2]: <./infrastructure/primary/presenter> "infraestructure/primary/presenter"  
[3.1.3]: <./infrastructure/primary/mapper> "infraestructure/primary/mapper"  
[3.1.4]: <./infrastructure/primary/query> "infrastructure/primary/query"  

[3.2]: <./infrastructure/secondary> "infrastructure/secondary"

[3.2.1]: <./infrastructure/secondary/mapper> "infrastructure/secondary/mapper"
[3.2.2]: <./infrastructure/secondary/model> "infrastructure/secondary/model"
[3.2.3]: <./infrastructure/secondary/persistence> "infrastructure/secondary/persistence"
[3.2.4]: <./infrastructure/secondary/service> "infrastructure/secondary/service"

[1]: <application> "application"

[1.1]: <application/usecase> "application/usecase"

[0]: <domain> "domain"

[0.1]: <domain/service> "domain/service"
[0.2]: <domain/model> "domain/model"
[0.2.1]: <domain/model/entity> "domain/model/entity"
[0.2.2]: <domain/model/valueobject> "domain/model/valueobject"
[0.3]: <domain/port> "domain/port"
