create table Cards
(
    is_deleted    char             default 'N' not null
        check ([is_deleted] = 'Y' OR [is_deleted] = 'N'),
    id            bigint identity
        primary key,
    amount        bigint           default 0
        check ([amount] >= 0),
    name          varchar(50)                  not null
        unique,
    bank_name     varchar(50)                  not null
        unique,
    currency_id   bigint                       not null,
    guid          uniqueidentifier default newid(),
    created_time  datetime2(3)     default getdate(),
    modified_time datetime2(3)     default getdate()
)
go

CREATE TRIGGER tr_afterUpdate_denyUpdGuidAndCreatModifDate_AND_setNewModTimeAndGuid_onCards ON Cards
    AFTER UPDATE AS
    IF (UPDATE(created_time) OR UPDATE(modified_time) OR UPDATE(guid))
        begin
            RAISERROR ('Columns "created_time", "modified_time" and "guid" can`t be updated!',16,1)
            ROLLBACK
        end
    ELSE
        begin
            SET NOCOUNT ON
            UPDATE dbo.Cards
            SET modified_time = getdate(), guid = newid()
            FROM dbo.Cards c
                     INNER JOIN inserted i
                                ON c.modified_time=i.modified_time
                                    AND c.guid=i.guid
        end
go

create view spt_values as
select name collate database_default as name,
       number,
       type collate database_default as type,
       low, high, status
from sys.spt_values
         go

grant select on spt_values to [public]
    go

create procedure dbo.sp_MScleanupmergepublisher
as
    exec sys.sp_MScleanupmergepublisher_internal
    go


create procedure dbo.sp_MSrepl_startup
as
    exec sys.sp_MSrepl_startup_internal
    go

