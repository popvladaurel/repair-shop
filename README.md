RepairShop
=============================

24.05.2017:  
````
Added button to import ANAF SSL/TLS certificate after deploy into our own key store. 
No more manually messing with JRE's cacerts Certificates File
````

To work, the WebApp needs a pre-existing PostgreSQL database called "RepairShop".

**Credits must go to:**

- password hashing accomplished using [Secure Password Storage v2.0](https://github.com/defuse/password-hashing)
- programmatically import of HTTPS certificates possible with help from [InstallCert](https://github.com/escline/InstallCert)

