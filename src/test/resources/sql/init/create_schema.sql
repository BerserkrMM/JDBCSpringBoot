create table Cards
(
    id                bigint    not null,
    amount            bigint,
    cardname          varchar,
    bankname          varchar,
    currencyid        bigint,
    uuid              uniqueidentifier,
    created_timestamp timestamp not null
)
    go

create table Currency
(
    id                bigint,
    name              varchar,
    code              varchar,
    exchangeratetousd numeric,
    uuid              uniqueidentifier,
    created           datetimeoffset default sysdatetimeoffset(),
    modified          datetimeoffset default sysdatetimeoffset() not null
)
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

