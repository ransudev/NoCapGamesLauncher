-- Simple users table for NoCapGamesLauncher
-- Run this in your MySQL server and update DB_URL/credentials in AuthManager.java

CREATE DATABASE IF NOT EXISTS nocap_launcher;
USE nocap_launcher;

CREATE TABLE IF NOT EXISTS users (
  id INT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(100) NOT NULL UNIQUE,
  password VARCHAR(255) NOT NULL
);
