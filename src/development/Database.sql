-- User to connect
  Database: repoIo
  User: repoIo
  Password: repoIoPass
  Repo: https://github.com/bmosqueda/repoIO.git

-- Create table:    DROP DATABASE repoIo;
  CREATE DATABASE repoIo;
  
-- Use database
  USE repoIo;

-- Catalogs
  CREATE TABLE schools(
    school_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(250) NOT NULL
  );

  CREATE TABLE categories(
    category_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL
  );

  /* Se validará que sólo se guarden letras y números, 
    sin otro tipo de caracteres 
    (los espacios se cambiarán por _)
  */
  CREATE TABLE keywords(
    keyword_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    Keyword VARCHAR(50) NOT NULL,
    CONSTRAINT Keyword_unique UNIQUE (Keyword)
  );

  CREATE INDEX keyword_index ON keywords(keyword);

  CREATE TABLE areas(
    area_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL
  );

  CREATE TABLE authors(
    author_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(150) NOT NULL,
    alias VARCHAR(100),
    country_of_birth VARCHAR(100) NOT NULL
  );

-- Main tables
  CREATE TABLE users(
    user_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    account_number VARCHAR(10) NOT NULL,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(32) NOT NULL,
    -- Admin = 1, validotor = 2 and common = 3
    role INT NOT NULL DEFAULT 3,  
    school_id INT NOT NULL,
    image VARCHAR(2083) DEFAULT '/repo.io/public/images/login.png',
    CONSTRAINT email_unique UNIQUE (email),
    CONSTRAINT school_reference_users 
      FOREIGN KEY (school_id)
      REFERENCES schools(school_id)
  );

  CREATE TABLE repositories(
    repository_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    creator_id INT NOT NULL,
    name VARCHAR(300) NOT NULL,
    description VARCHAR(3000) NOT NULL,
    url VARCHAR(2083) NOT NULL,
    tags VARCHAR(300) NOT NULL,
    CONSTRAINT creator_reference_repositories
      FOREIGN KEY (creator_id)
      REFERENCES users(user_id)
  );

  CREATE TABLE resources(
    resource_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(250) NOT NULL,
    description TEXT,
    -- in MB
    size INT NOT NULL,
    -- The resources only can belong to one repository
    repository_id INT NOT NULL,
    -- type ENUM('video', 'audio', 'book', 'document', 'other') NOT NULL,
    type INT DEFAULT 5 COMMENT 'video, audio, book, document, other',
    url VARCHAR(400) NOT NULL,
    CONSTRAINT repository_reference_resources
      FOREIGN KEY (repository_id)
      REFERENCES repositories(repository_id)
  );

-- Intermeadiate tables
  CREATE TABLE categories_repository(
    repository_id INT NOT NULL,
    category_id INT NOT NULL,
    CONSTRAINT repository_reference_categories_repository
      FOREIGN KEY (repository_id)
      REFERENCES repositories(repository_id),
    CONSTRAINT category_reference_categories_repository
      FOREIGN KEY (category_id)
      REFERENCES categories(category_id),
    PRIMARY KEY (repository_id, category_id)
  );

  -- En lugar de guardar las etiquestas del repositorio, mejor se guardan qué repositorios tienen qué etiquetas
  CREATE TABLE repositories_with_keyword(
    repository_id INT NOT NULL,
    keyword_id INT NOT NULL,
    CONSTRAINT repository_reference_repositories_keyword
      FOREIGN KEY (repository_id)
      REFERENCES repositories(repository_id),
    CONSTRAINT keyword_reference_repositories_keyword 
      FOREIGN KEY (keyword_id)
      REFERENCES keywords(keyword_id),
    PRIMARY KEY (repository_id, keyword_id)
  );

  CREATE TABLE areas_resource(
    area_id INT NOT NULL,
    resource_id INT NOT NULL,
    CONSTRAINT area_reference_areas_resource 
      FOREIGN KEY areas(area_id)
      REFERENCES areas(area_id),
    CONSTRAINT resource_reference_areas_resource
      FOREIGN KEY resources(resource_id)
      REFERENCES resources(resource_id),
    PRIMARY KEY (area_id, resource_id)
  );

  CREATE TABLE authors_resource(
    author_id INT NOT NULL,
    resource_id INT NOT NULL,
    CONSTRAINT author_reference_authors_resource 
      FOREIGN KEY (author_id)
      REFERENCES authors(author_id),
    CONSTRAINT resource_reference_authors_resource 
      FOREIGN KEY (resource_id)
      REFERENCES resources(resource_id),
    PRIMARY KEY (author_id, resource_id)
  );

-- Seeds
  -- Schools
    INSERT INTO schools (name) VALUES('Facultad de telemática');
    INSERT INTO schools (name) VALUES('Facultad de contabilidad');
    INSERT INTO schools (name) VALUES('Facultad de derecho');
    INSERT INTO schools (name) VALUES('Facultad de medicina');
    
  -- Users
    INSERT INTO users (name, account_number, email, password, role, school_id) VALUES('Brandon Mosqueda', '20145969', 'bmosqueda@ucol.mx', "hola", 1, 1);
    INSERT INTO users (name, account_number, email, password, role, school_id) VALUES('Charley Jaculina', '10821935', 'plumiform@ucol.mx', "hola", 3, 1);
    INSERT INTO users (name, account_number, email, password, role, school_id) VALUES('Tia Bonhomme', '62613759', 'tbonhome@ucol.mx', "hola", 3, 1);

  -- Authors
    INSERT INTO authors (name, alias, country_of_birth) VALUES('Zona Scharmann', 'Z. Scharmann', 'Philippines');
    INSERT INTO authors (name, alias, country_of_birth) VALUES('Etha Nikolai', 'E. Nikolai', 'Nicaragua');
    INSERT INTO authors (name, alias, country_of_birth) VALUES('Simon Munks', 'S. Munks', 'South Sudan');

  -- Repositories
    INSERT INTO repositories (creator_id, name, url, tags) VALUES (1, 'El repo', 'url', 'los, tags, chidos');
    INSERT INTO repositories (creator_id, name, url, tags) VALUES (1, 'El repo 2', 'url 2', 'los, tags, chidos 2');

  -- Resources
    INSERT INTO resources (title, description, size, repository_id, type, url) VALUES ('Recurso 1', 'description', 4, 1, 1, 'url');

  -- Categories Repository
    INSERT INTO categories_repository (repository_id, category_id) VALUES (2, 2);

  -- Area
    INSERT INTO areas (name) VALUES('Area chida');
    INSERT INTO areas (name) VALUES('Area chida 2');

  -- Areas Resource
    INSERT INTO areas_resource (resource_id, area_id) VALUES (1, 1);
    INSERT INTO areas_resource (resource_id, area_id) VALUES (1, 2);

    INSERT INTO areas_resource (resource_id, area_id) VALUES (2, 2);
    INSERT INTO areas_resource (resource_id, area_id) VALUES (2, 1);

  -- Keywords
    INSERT INTO keywords (keyword) VALUES('hola');
    INSERT INTO keywords (keyword) VALUES('mundo');
    INSERT INTO keywords (keyword) VALUES('otro');

  -- Keyword repository
    INSERT INTO repositories_with_keyword (repository_id, keyword_id) VALUES (1, 1);
    INSERT INTO repositories_with_keyword (repository_id, keyword_id) VALUES (1, 2);
    INSERT INTO repositories_with_keyword (repository_id, keyword_id) VALUES (2, 1);

-- Grants
  GRANT ALL PRIVILEGES ON repoIo.* TO repoIo@localhost;