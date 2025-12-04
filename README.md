YatraSuite
===

The ```microservice``` for travelling management.


## Services

---

```
/
├── common-service
├── ride-service
├── user-service
├── vehicle-service
├── yatra-thymeleaf-webapp
├── yatra-webapp
│    ├── README.md                  # Instructions and helpful links
│    └── /
├── README.md                       # Instructions and helpful links
├── robots.txt                      # tells which URLs the search engine crawlers can access on your site
├── <module>                        # The module service
└── /
```

### API Calls

- Get All Records

```shell
curl http://localhost:8080/api/v1/yatrasuite
```

- Filter by ID

```shell
curl http://localhost:8080/api/v1/yatrasuite/2
```


## Requirements

- [OpenJDK 21](https://openjdk.org/projects/jdk/21/)
- [Apache Maven 3.9+](https://maven.apache.org/download.cgi)


## Git Setup

If `.mvn` folders are being tracked by git (they should be ignored), run the following to remove them from git tracking:

```bash
# Remove .mvn folders from git tracking (keeps the files locally)
git rm -r --cached **/.mvn/

# Then commit the change
git commit -m "Stop tracking .mvn folders"
```

This removes the `.mvn` folders from git's tracking while keeping them on your local filesystem.


# Reference

---


# Author

---

- Rohtash Lakra
