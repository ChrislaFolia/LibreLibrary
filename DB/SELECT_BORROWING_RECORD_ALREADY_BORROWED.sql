USE LibreLibrary
GO

CREATE PROCEDURE SELECT_BORROWING_RECORD_ALREADY_BORROWED
	@userId INT

AS BEGIN 
	
	BEGIN TRY
      BEGIN TRANSACTION;

			SELECT br.recordId, br.inventoryId, b.isbn, b.name, b.author, br.borrowingTime, br.userId  
			FROM BorrowingRecord br JOIN Inventory i ON (br.inventoryId=i.inventoryId)
									JOIN Book b ON (i.isbn=b.isbn)
			WHERE br.userId = @userId AND br.returnTime IS NULL;
	 COMMIT;
   END TRY
   BEGIN CATCH
      ROLLBACK;  
      
      THROW;
   END CATCH

END
GO