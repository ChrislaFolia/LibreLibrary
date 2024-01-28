USE LibreLibrary
GO

CREATE PROCEDURE UPDATE_BORROWING_RECORD_RETURN
	@userId INT,
	@inventoryId INT

AS BEGIN 

   BEGIN TRY
      BEGIN TRANSACTION;
      
      -- Step 1: Update BorrowingRecord
      DECLARE @returnTime DATETIME2 =  GETDATE();

      UPDATE BorrowingRecord
      SET returnTime = @returnTime
	  WHERE userId=@userId;

      -- Step 2: Update Inventory status 
      UPDATE Inventory
		SET status = '¦b®w'
	    WHERE inventoryId= @inventoryId;
      
      COMMIT;
   END TRY
   BEGIN CATCH
      ROLLBACK;  
      
      THROW;
   END CATCH
END
GO