USE LibreLibrary
GO

CREATE PROCEDURE INSERT_BORROWING_RECORD_BORROW
	@userId INT,
	@inventoryId INT

AS BEGIN 

   BEGIN TRY
      BEGIN TRANSACTION;
      
      -- Step 1: Insert BorrowingRecord
      DECLARE @borrowingTime DATETIME2 = GETDATE();
      DECLARE @returnTime DATETIME2 = NULL;  

      INSERT INTO BorrowingRecord (userId, inventoryId, borrowingTime, returnTime)
      VALUES (@userId, @inventoryId, @borrowingTime, @returnTime);

      -- Step 2: Update Inventory status 
      UPDATE Inventory
		SET status = '¥X­É¤¤'
	    WHERE inventoryId= @inventoryId;
      
      COMMIT;
   END TRY
   BEGIN CATCH
      ROLLBACK;  
      
      THROW;
   END CATCH
END
GO
