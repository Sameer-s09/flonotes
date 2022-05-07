
DROP TABLE IF EXISTS StickyNote;
CREATE TABLE StickyNote (
noteUUID VARCHAR(36) PRIMARY KEY,
status VARCHAR(50) NOT NULL,
notetext VARCHAR(50),
lastupdated timestamp
);