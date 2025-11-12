-- SQL Manager 2005 for SQL Server (2.2.0.1)
-- ---------------------------------------
-- Host     : (local)
-- Database : ormawin


--
-- Structure for table scambio_itineris : 
--

CREATE TABLE [dbo].[scambio_itineris] (
  [id_spedizione] int,
  [data] datetime NOT NULL,
  [cod_kit] varchar(20) COLLATE Latin1_General_CI_AS,
  [descr_kit] varchar(250) COLLATE Latin1_General_CI_AS NOT NULL,
  [id_kit] varchar(20) COLLATE Latin1_General_CI_AS NOT NULL,
  [data_scad] datetime NOT NULL,
  [fl_usato] char(1) COLLATE Latin1_General_CI_AS NOT NULL,
  [id_intervento] int,
  [last_upd_ormawin] datetime,
  [last_upd_itineris] datetime,
  [lotto] varchar(255) COLLATE Latin1_General_CI_AS
)
ON [PRIMARY]
GO

