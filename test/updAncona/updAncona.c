# include "FlagShip.h"

static FSchar *fgs_file_name = "updancona";
static FSvoid init_ref_data();
extern int    fgsDonotenter;

extern FSvar *_bb_get_assign_me FGS_ANSI((FSint, FSvar *[]));
extern FSvar *_bb_get_backspace_me FGS_ANSI((FSint, FSvar *[]));
extern FSvar *_bb_get_baddate_me FGS_ANSI((FSint, FSvar *[]));
extern FSvar *_bb_get_baddate_ac FGS_ANSI((FSvar *));
extern FSvar *_bb_get_block_ac FGS_ANSI((FSvar *));
extern FSvar *_bb_get_block_as FGS_ANSI((FSvar *, FSvar *));
extern FSvar *_bb_get_changed_ac FGS_ANSI((FSvar *));
extern FSvar *_bb_get_colordisp_me FGS_ANSI((FSint, FSvar *[]));
extern FSvar *_bb_get_decpos_ac FGS_ANSI((FSvar *));
extern FSvar *_bb_get_delend_me FGS_ANSI((FSint, FSvar *[]));
extern FSvar *_bb_get_delete_me FGS_ANSI((FSint, FSvar *[]));
extern FSvar *_bb_get_delleft_me FGS_ANSI((FSint, FSvar *[]));
extern FSvar *_bb_get_delright_me FGS_ANSI((FSint, FSvar *[]));
extern FSvar *_bb_get_delwordleft_me FGS_ANSI((FSint, FSvar *[]));
extern FSvar *_bb_get_delwordright_me FGS_ANSI((FSint, FSvar *[]));
extern FSvar *_bb_get_display_me FGS_ANSI((FSint, FSvar *[]));
extern FSvar *_bb_get_emptydate_ac FGS_ANSI((FSvar *));
extern FSvar *_bb_get_end_me FGS_ANSI((FSint, FSvar *[]));
extern FSvar *_bb_get_hasfocus_ac FGS_ANSI((FSvar *));
extern FSvar *_bb_get_home_me FGS_ANSI((FSint, FSvar *[]));
extern FSvar *_bb_get_init_me FGS_ANSI((FSint, FSvar *[]));
extern FSvar *_bb_get_insert_me FGS_ANSI((FSint, FSvar *[]));
extern FSvar *_bb_get_killfocus_me FGS_ANSI((FSint, FSvar *[]));
extern FSvar *_bb_get_left_me FGS_ANSI((FSint, FSvar *[]));
extern FSvar *_bb_get_original_ac FGS_ANSI((FSvar *));
extern FSvar *_bb_get_overstrike_me FGS_ANSI((FSint, FSvar *[]));
extern FSvar *_bb_get_picture_ac FGS_ANSI((FSvar *));
extern FSvar *_bb_get_picture_as FGS_ANSI((FSvar *, FSvar *));
extern FSvar *_bb_get_pos_ac FGS_ANSI((FSvar *));
extern FSvar *_bb_get_rejected_ac FGS_ANSI((FSvar *));
extern FSvar *_bb_get_reset_me FGS_ANSI((FSint, FSvar *[]));
extern FSvar *_bb_get_right_me FGS_ANSI((FSint, FSvar *[]));
extern FSvar *_bb_get_setfocus_me FGS_ANSI((FSint, FSvar *[]));
extern FSvar *_bb_get_todecpos_me FGS_ANSI((FSint, FSvar *[]));
extern FSvar *_bb_get_type_ac FGS_ANSI((FSvar *));
extern FSvar *_bb_get_typeout_ac FGS_ANSI((FSvar *));
extern FSvar *_bb_get_undo_me FGS_ANSI((FSint, FSvar *[]));
extern FSvar *_bb_get_untransform_me FGS_ANSI((FSint, FSvar *[]));
extern FSvar *_bb_get_updatebuffer_me FGS_ANSI((FSint, FSvar *[]));
extern FSvar *_bb_get_varget_me FGS_ANSI((FSint, FSvar *[]));
extern FSvar *_bb_get_varput_me FGS_ANSI((FSint, FSvar *[]));
extern FSvar *_bb_get_wordleft_me FGS_ANSI((FSint, FSvar *[]));
extern FSvar *_bb_get_wordright_me FGS_ANSI((FSint, FSvar *[]));
# define GetAssignMethod 0
# define GetBackspaceMethod 1
# define GetBaddateMethod 2
# define GetBaddateAccess 3
# define GetBlockAccess 4
# define GetBlockAssign 5
# define GetChangedAccess 6
# define GetColordispMethod 7
# define GetDecposAccess 8
# define GetDelendMethod 9
# define GetDeleteMethod 10
# define GetDelleftMethod 11
# define GetDelrightMethod 12
# define GetDelwordleftMethod 13
# define GetDelwordrightMethod 14
# define GetDisplayMethod 15
# define GetEmptydateAccess 16
# define GetEndMethod 17
# define GetHasfocusAccess 18
# define GetHomeMethod 19
# define GetInitMethod 20
# define GetInsertMethod 21
# define GetKillfocusMethod 22
# define GetLeftMethod 23
# define GetOriginalAccess 24
# define GetOverstrikeMethod 25
# define GetPictureAccess 26
# define GetPictureAssign 27
# define GetPosAccess 28
# define GetRejectedAccess 29
# define GetResetMethod 30
# define GetRightMethod 31
# define GetSetfocusMethod 32
# define GetTodecposMethod 33
# define GetTypeAccess 34
# define GetTypeoutAccess 35
# define GetUndoMethod 36
# define GetUntransformMethod 37
# define GetUpdatebufferMethod 38
# define GetVargetMethod 39
# define GetVarputMethod 40
# define GetWordleftMethod 41
# define GetWordrightMethod 42
# define Get_a_flagProtect 2
# define Get_block_getProtect 3
# define Get_buff_begProtect 4
# define Get_buff_lngProtect 5
# define Get_colorselProtect 6
# define Get_e_flagProtect 7
# define Get_orig_nameProtect 8
# define Get_picfunctProtect 9
# define Get_pict_lngProtect 10
# define Get_pictemplProtect 11
# define Get_s_flagProtect 12
# define Get_var_evalProtect 13
# define Get_var_ptrProtect 14
# define Get_var_stypeProtect 15
# define Get_x_flagProtect 16
# define GetBaddateProtect 17
# define GetBaddateInstance 18
# define GetBlockProtect 19
# define GetBlockInstance 20
# define GetBufferInstance 21
# define GetCargoInstance 22
# define GetChangedProtect 23
# define GetChangedInstance 24
# define GetClearInstance 25
# define GetColInstance 26
# define GetColorspecInstance 27
# define GetDecposProtect 28
# define GetDecposInstance 29
# define GetEmptydateProtect 30
# define GetEmptydateInstance 31
# define GetExitstateInstance 32
# define GetHasfocusProtect 33
# define GetHasfocusInstance 34
# define GetMinusInstance 35
# define GetNameInstance 36
# define GetOriginalProtect 37
# define GetOriginalInstance 38
# define GetPictureProtect 39
# define GetPictureInstance 40
# define GetPosProtect 41
# define GetPosInstance 42
# define GetPostblockInstance 43
# define GetPreblockInstance 44
# define GetReaderInstance 45
# define GetRejectedProtect 46
# define GetRejectedInstance 47
# define GetRowInstance 48
# define GetSubscriptInstance 49
# define GetTypeProtect 50
# define GetTypeInstance 51
# define GetTypeoutProtect 52
# define GetTypeoutInstance 53
# define ErrorArgInstance 2
# define ErrorArgnumInstance 3
# define ErrorArgsInstance 4
# define ErrorArgtypereqInstance 5
# define ErrorCallfuncsyInstance 6
# define ErrorCandefaultInstance 7
# define ErrorCanretryInstance 8
# define ErrorCansubstitInstance 9
# define ErrorCargoInstance 10
# define ErrorChoiceInstance 11
# define ErrorDescriptioInstance 12
# define ErrorFilehandleInstance 13
# define ErrorFilenameInstance 14
# define ErrorFuncptrInstance 15
# define ErrorFuncsymInstance 16
# define ErrorGencodeInstance 17
# define ErrorMaxsizeInstance 18
# define ErrorMethodselfInstance 19
# define ErrorOperationInstance 20
# define ErrorOscodeInstance 21
# define ErrorSeverityInstance 22
# define ErrorSubcodeInstance 23
# define ErrorSubcodetexInstance 24
# define ErrorSubstituteInstance 25
# define ErrorSubsystemInstance 26
# define ErrorTriesInstance 27
extern FSvar *_bb_tbrowse_addcolumn_me FGS_ANSI((FSint, FSvar *[]));
extern FSvar *_bb_tbrowse_colcount_ac FGS_ANSI((FSvar *));
extern FSvar *_bb_tbrowse_colorrect_me FGS_ANSI((FSint, FSvar *[]));
extern FSvar *_bb_tbrowse_colpos_ac FGS_ANSI((FSvar *));
extern FSvar *_bb_tbrowse_colpos_as FGS_ANSI((FSvar *, FSvar *));
extern FSvar *_bb_tbrowse_colsep_ac FGS_ANSI((FSvar *));
extern FSvar *_bb_tbrowse_colsep_as FGS_ANSI((FSvar *, FSvar *));
extern FSvar *_bb_tbrowse_colwidth_me FGS_ANSI((FSint, FSvar *[]));
extern FSvar *_bb_tbrowse_configure_me FGS_ANSI((FSint, FSvar *[]));
extern FSvar *_bb_tbrowse_dehilite_me FGS_ANSI((FSint, FSvar *[]));
extern FSvar *_bb_tbrowse_delcolumn_me FGS_ANSI((FSint, FSvar *[]));
extern FSvar *_bb_tbrowse_down_me FGS_ANSI((FSint, FSvar *[]));
extern FSvar *_bb_tbrowse_end_me FGS_ANSI((FSint, FSvar *[]));
extern FSvar *_bb_tbrowse_footsep_ac FGS_ANSI((FSvar *));
extern FSvar *_bb_tbrowse_footsep_as FGS_ANSI((FSvar *, FSvar *));
extern FSvar *_bb_tbrowse_forcestabl_me FGS_ANSI((FSint, FSvar *[]));
extern FSvar *_bb_tbrowse_forcestable_me FGS_ANSI((FSint, FSvar *[]));
extern FSvar *_bb_tbrowse_freeze_ac FGS_ANSI((FSvar *));
extern FSvar *_bb_tbrowse_freeze_as FGS_ANSI((FSvar *, FSvar *));
extern FSvar *_bb_tbrowse_getcolumn_me FGS_ANSI((FSint, FSvar *[]));
extern FSvar *_bb_tbrowse_gobottom_me FGS_ANSI((FSint, FSvar *[]));
extern FSvar *_bb_tbrowse_gomousepos_me FGS_ANSI((FSint, FSvar *[]));
extern FSvar *_bb_tbrowse_gotop_me FGS_ANSI((FSint, FSvar *[]));
extern FSvar *_bb_tbrowse_headsep_ac FGS_ANSI((FSvar *));
extern FSvar *_bb_tbrowse_headsep_as FGS_ANSI((FSvar *, FSvar *));
extern FSvar *_bb_tbrowse_hilite_me FGS_ANSI((FSint, FSvar *[]));
extern FSvar *_bb_tbrowse_home_me FGS_ANSI((FSint, FSvar *[]));
extern FSvar *_bb_tbrowse_init_me FGS_ANSI((FSint, FSvar *[]));
extern FSvar *_bb_tbrowse_inscolumn_me FGS_ANSI((FSint, FSvar *[]));
extern FSvar *_bb_tbrowse_invalidate_me FGS_ANSI((FSint, FSvar *[]));
extern FSvar *_bb_tbrowse_left_me FGS_ANSI((FSint, FSvar *[]));
extern FSvar *_bb_tbrowse_leftvisibl_ac FGS_ANSI((FSvar *));
extern FSvar *_bb_tbrowse_linecursor_ac FGS_ANSI((FSvar *));
extern FSvar *_bb_tbrowse_linecursor_as FGS_ANSI((FSvar *, FSvar *));
extern FSvar *_bb_tbrowse_nbottom_ac FGS_ANSI((FSvar *));
extern FSvar *_bb_tbrowse_nbottom_as FGS_ANSI((FSvar *, FSvar *));
extern FSvar *_bb_tbrowse_nleft_ac FGS_ANSI((FSvar *));
extern FSvar *_bb_tbrowse_nleft_as FGS_ANSI((FSvar *, FSvar *));
extern FSvar *_bb_tbrowse_nright_ac FGS_ANSI((FSvar *));
extern FSvar *_bb_tbrowse_nright_as FGS_ANSI((FSvar *, FSvar *));
extern FSvar *_bb_tbrowse_ntop_ac FGS_ANSI((FSvar *));
extern FSvar *_bb_tbrowse_ntop_as FGS_ANSI((FSvar *, FSvar *));
extern FSvar *_bb_tbrowse_pagedown_me FGS_ANSI((FSint, FSvar *[]));
extern FSvar *_bb_tbrowse_pageup_me FGS_ANSI((FSint, FSvar *[]));
extern FSvar *_bb_tbrowse_panend_me FGS_ANSI((FSint, FSvar *[]));
extern FSvar *_bb_tbrowse_panhome_me FGS_ANSI((FSint, FSvar *[]));
extern FSvar *_bb_tbrowse_panleft_me FGS_ANSI((FSint, FSvar *[]));
extern FSvar *_bb_tbrowse_panright_me FGS_ANSI((FSint, FSvar *[]));
extern FSvar *_bb_tbrowse_refreshall_me FGS_ANSI((FSint, FSvar *[]));
extern FSvar *_bb_tbrowse_refreshcur_me FGS_ANSI((FSint, FSvar *[]));
extern FSvar *_bb_tbrowse_refreshcurrent_me FGS_ANSI((FSint, FSvar *[]));
extern FSvar *_bb_tbrowse_right_me FGS_ANSI((FSint, FSvar *[]));
extern FSvar *_bb_tbrowse_rightvisib_ac FGS_ANSI((FSvar *));
extern FSvar *_bb_tbrowse_rowcount_ac FGS_ANSI((FSvar *));
extern FSvar *_bb_tbrowse_setcolumn_me FGS_ANSI((FSint, FSvar *[]));
extern FSvar *_bb_tbrowse_stabilize_me FGS_ANSI((FSint, FSvar *[]));
extern FSvar *_bb_tbrowse_up_me FGS_ANSI((FSint, FSvar *[]));
# define TbrowseAddcolumnMethod 0
# define TbrowseColcountAccess 1
# define TbrowseColorrectMethod 2
# define TbrowseColposAccess 3
# define TbrowseColposAssign 4
# define TbrowseColsepAccess 5
# define TbrowseColsepAssign 6
# define TbrowseColwidthMethod 7
# define TbrowseConfigureMethod 8
# define TbrowseDehiliteMethod 9
# define TbrowseDelcolumnMethod 10
# define TbrowseDownMethod 11
# define TbrowseEndMethod 12
# define TbrowseFootsepAccess 13
# define TbrowseFootsepAssign 14
# define TbrowseForcestablMethod 15
# define TbrowseForcestableMethod 16
# define TbrowseFreezeAccess 17
# define TbrowseFreezeAssign 18
# define TbrowseGetcolumnMethod 19
# define TbrowseGobottomMethod 20
# define TbrowseGomouseposMethod 21
# define TbrowseGotopMethod 22
# define TbrowseHeadsepAccess 23
# define TbrowseHeadsepAssign 24
# define TbrowseHiliteMethod 25
# define TbrowseHomeMethod 26
# define TbrowseInitMethod 27
# define TbrowseInscolumnMethod 28
# define TbrowseInvalidateMethod 29
# define TbrowseLeftMethod 30
# define TbrowseLeftvisiblAccess 31
# define TbrowseLinecursorAccess 32
# define TbrowseLinecursorAssign 33
# define TbrowseNbottomAccess 34
# define TbrowseNbottomAssign 35
# define TbrowseNleftAccess 36
# define TbrowseNleftAssign 37
# define TbrowseNrightAccess 38
# define TbrowseNrightAssign 39
# define TbrowseNtopAccess 40
# define TbrowseNtopAssign 41
# define TbrowsePagedownMethod 42
# define TbrowsePageupMethod 43
# define TbrowsePanendMethod 44
# define TbrowsePanhomeMethod 45
# define TbrowsePanleftMethod 46
# define TbrowsePanrightMethod 47
# define TbrowseRefreshallMethod 48
# define TbrowseRefreshcurMethod 49
# define TbrowseRefreshcurrentMethod 50
# define TbrowseRightMethod 51
# define TbrowseRightvisibAccess 52
# define TbrowseRowcountAccess 53
# define TbrowseSetcolumnMethod 54
# define TbrowseStabilizeMethod 55
# define TbrowseUpMethod 56
# define Tbrowse_columnsProtect 2
# define Tbrowse_cr_bg_colProtect 3
# define Tbrowse_cr_bottomProtect 4
# define Tbrowse_cr_fg_colProtect 5
# define Tbrowse_cr_leftProtect 6
# define Tbrowse_cr_rightProtect 7
# define Tbrowse_cr_topProtect 8
# define Tbrowse_fcolProtect 9
# define Tbrowse_iscreenProtect 10
# define Tbrowse_n_olineProtect 11
# define Tbrowse_n_stableProtect 12
# define Tbrowse_noffsetProtect 13
# define TbrowseAutoliteInstance 14
# define TbrowseCargoInstance 15
# define TbrowseColcountProtect 16
# define TbrowseColcountInstance 17
# define TbrowseColorspecInstance 18
# define TbrowseColposProtect 19
# define TbrowseColposInstance 20
# define TbrowseColsepProtect 21
# define TbrowseColsepInstance 22
# define TbrowseFootsepProtect 23
# define TbrowseFootsepInstance 24
# define TbrowseFreezeProtect 25
# define TbrowseFreezeInstance 26
# define TbrowseGobottomblInstance 27
# define TbrowseGotopblockInstance 28
# define TbrowseHeadsepProtect 29
# define TbrowseHeadsepInstance 30
# define TbrowseHitbottomInstance 31
# define TbrowseHittopInstance 32
# define TbrowseLeftvisiblProtect 33
# define TbrowseLeftvisiblInstance 34
# define TbrowseLinecursorInstance 35
# define TbrowseNbottomProtect 36
# define TbrowseNbottomInstance 37
# define TbrowseNleftProtect 38
# define TbrowseNleftInstance 39
# define TbrowseNrightProtect 40
# define TbrowseNrightInstance 41
# define TbrowseNtopProtect 42
# define TbrowseNtopInstance 43
# define TbrowseRightvisibProtect 44
# define TbrowseRightvisibInstance 45
# define TbrowseRowcountProtect 46
# define TbrowseRowcountInstance 47
# define TbrowseRowposInstance 48
# define TbrowseSkipblockInstance 49
# define TbrowseStableInstance 50
extern FSvar *_bb_tbcolumn_block_ac FGS_ANSI((FSvar *));
extern FSvar *_bb_tbcolumn_block_as FGS_ANSI((FSvar *, FSvar *));
extern FSvar *_bb_tbcolumn_cargo_ac FGS_ANSI((FSvar *));
extern FSvar *_bb_tbcolumn_cargo_as FGS_ANSI((FSvar *, FSvar *));
extern FSvar *_bb_tbcolumn_colorblock_ac FGS_ANSI((FSvar *));
extern FSvar *_bb_tbcolumn_colorblock_as FGS_ANSI((FSvar *, FSvar *));
extern FSvar *_bb_tbcolumn_colsep_ac FGS_ANSI((FSvar *));
extern FSvar *_bb_tbcolumn_colsep_as FGS_ANSI((FSvar *, FSvar *));
extern FSvar *_bb_tbcolumn_defcolor_ac FGS_ANSI((FSvar *));
extern FSvar *_bb_tbcolumn_defcolor_as FGS_ANSI((FSvar *, FSvar *));
extern FSvar *_bb_tbcolumn_footing_ac FGS_ANSI((FSvar *));
extern FSvar *_bb_tbcolumn_footing_as FGS_ANSI((FSvar *, FSvar *));
extern FSvar *_bb_tbcolumn_footsep_ac FGS_ANSI((FSvar *));
extern FSvar *_bb_tbcolumn_footsep_as FGS_ANSI((FSvar *, FSvar *));
extern FSvar *_bb_tbcolumn_heading_ac FGS_ANSI((FSvar *));
extern FSvar *_bb_tbcolumn_heading_as FGS_ANSI((FSvar *, FSvar *));
extern FSvar *_bb_tbcolumn_headsep_ac FGS_ANSI((FSvar *));
extern FSvar *_bb_tbcolumn_headsep_as FGS_ANSI((FSvar *, FSvar *));
extern FSvar *_bb_tbcolumn_init_me FGS_ANSI((FSint, FSvar *[]));
extern FSvar *_bb_tbcolumn_picture_ac FGS_ANSI((FSvar *));
extern FSvar *_bb_tbcolumn_picture_as FGS_ANSI((FSvar *, FSvar *));
extern FSvar *_bb_tbcolumn_width_ac FGS_ANSI((FSvar *));
extern FSvar *_bb_tbcolumn_width_as FGS_ANSI((FSvar *, FSvar *));
# define TbcolumnBlockAccess 0
# define TbcolumnBlockAssign 1
# define TbcolumnCargoAccess 2
# define TbcolumnCargoAssign 3
# define TbcolumnColorblockAccess 4
# define TbcolumnColorblockAssign 5
# define TbcolumnColsepAccess 6
# define TbcolumnColsepAssign 7
# define TbcolumnDefcolorAccess 8
# define TbcolumnDefcolorAssign 9
# define TbcolumnFootingAccess 10
# define TbcolumnFootingAssign 11
# define TbcolumnFootsepAccess 12
# define TbcolumnFootsepAssign 13
# define TbcolumnHeadingAccess 14
# define TbcolumnHeadingAssign 15
# define TbcolumnHeadsepAccess 16
# define TbcolumnHeadsepAssign 17
# define TbcolumnInitMethod 18
# define TbcolumnPictureAccess 19
# define TbcolumnPictureAssign 20
# define TbcolumnWidthAccess 21
# define TbcolumnWidthAssign 22
# define TbcolumnBlockProtect 2
# define TbcolumnBlockInstance 3
# define TbcolumnCargoProtect 4
# define TbcolumnCargoInstance 5
# define TbcolumnColorblockProtect 6
# define TbcolumnColorblockInstance 7
# define TbcolumnColsepProtect 8
# define TbcolumnColsepInstance 9
# define TbcolumnDefcolorProtect 10
# define TbcolumnDefcolorInstance 11
# define TbcolumnFootingProtect 12
# define TbcolumnFootingInstance 13
# define TbcolumnFootsepProtect 14
# define TbcolumnFootsepInstance 15
# define TbcolumnHeadingProtect 16
# define TbcolumnHeadingInstance 17
# define TbcolumnHeadsepProtect 18
# define TbcolumnHeadsepInstance 19
# define TbcolumnPictureProtect 20
# define TbcolumnPictureInstance 21
# define TbcolumnWidthProtect 22
# define TbcolumnWidthInstance 23
extern FSvar *_bb_dbcloseare FGS_ANSI((FSint, FSvar *[]));
extern FSvar *_bb_dbgotop FGS_ANSI((FSint, FSvar *[]));
extern FSvar *_bb_dbseek FGS_ANSI((FSint, FSvar *[]));
extern FSvar *_bb_dbsetindex FGS_ANSI((FSint, FSvar *[]));
extern FSvar *_bb_dbskip FGS_ANSI((FSint, FSvar *[]));
extern FSvar *_bb_dbusearea FGS_ANSI((FSint, FSvar *[]));
extern FSvar *_bb_devout FGS_ANSI((FSint, FSvar *[]));
extern FSvar *_bb_devpos FGS_ANSI((FSint, FSvar *[]));
extern FSvar *_bb_eof FGS_ANSI((FSint, FSvar *[]));
extern FSvar *_bb_if FGS_ANSI((FSint, FSvar *[]));
extern FSvar *_bb_ltrim FGS_ANSI((FSint, FSvar *[]));
extern FSvar *_bb_str FGS_ANSI((FSint, FSvar *[]));
extern FSvar *_bb___quit FGS_ANSI((FSint, FSvar *[]));
extern FSvar *_bb_updancona FGS_ANSI((FSint, FSvar *[]));
static FSint _fsali_anc;
static FSint _fsali_kit;
extern FSvar *_bb_gettkit FGS_ANSI((FSint, FSvar *[]));
extern FSvar *_bb_getkitv FGS_ANSI((FSint, FSvar *[]));
static FSint _fsali_tkit;
static FSint _fsali_kitv;


static FSint _bbvar_id_kit;
static FSint _bbvar_idtipokit;
static FSint _bbvar_categoria;
static FSint _bbvar_desc_kit;
static FSint _bbvar_nil;
static FSint _bbvar_tnome;
static FSint _bbvar_nome;

/* *  eof  */
FSvar * _bb_updancona (parno, parptr)
FSint   parno;
FSvar * parptr[];
{
	fn_stack fn_stk;
	FSchar *__who_me=fgs_fn_start(&fn_stk,fgs_file_name,"updancona",2,parno);

	FSvar *par0[7], *par1[7], *par2[4];
	if(fgsDonotenter) init_ref_data();
	_fgs_signal_a(1);
#line 1 "updAncona.prg"
	par0[0] = mk_var_cp(TRUE_VAR);
	par0[1] = cre_tmpvar();
	par0[2] = set_cv("kit_vari");
	par0[3] = set_cv("KITV");
goto lbor0;
lbor0:;
if(1) {
	par1[1] = TRUE_VAR;
	par1[0] = par1[1];
} else {
	par1[2] = fgsGetVar(_bbvar_nil);
	par1[0] = par1[2];
}
	par0[4] = par1[0];
	par0[5] = mk_var_cp(FALSE_VAR);
	par0[6] = _bb_dbusearea(6, &par0[0]);
	_fgs_signal_a(2);
#line 2 "updAncona.prg"
	par0[0] = set_cv("kvari_id");
	par0[1] = _bb_dbsetindex(1, &par0[0]);
	_fgs_signal_a(3);
#line 3 "updAncona.prg"
	par0[0] = mk_var_cp(TRUE_VAR);
	par0[1] = cre_tmpvar();
	par0[2] = set_cv("tipokit");
	par0[3] = set_cv("TKIT");
goto lbor1;
lbor1:;
if(1) {
	par1[1] = TRUE_VAR;
	par1[0] = par1[1];
} else {
	par1[2] = fgsGetVar(_bbvar_nil);
	par1[0] = par1[2];
}
	par0[4] = par1[0];
	par0[5] = mk_var_cp(FALSE_VAR);
	par0[6] = _bb_dbusearea(6, &par0[0]);
	_fgs_signal_a(4);
#line 4 "updAncona.prg"
	par0[0] = set_cv("tikit_id");
	par0[1] = _bb_dbsetindex(1, &par0[0]);
	_fgs_signal_a(5);
#line 5 "updAncona.prg"
	par0[0] = mk_var_cp(TRUE_VAR);
	par0[1] = cre_tmpvar();
	par0[2] = set_cv("kit");
	par0[3] = set_cv("KIT");
goto lbor2;
lbor2:;
if(1) {
	par1[1] = TRUE_VAR;
	par1[0] = par1[1];
} else {
	par1[2] = fgsGetVar(_bbvar_nil);
	par1[0] = par1[2];
}
	par0[4] = par1[0];
	par0[5] = mk_var_cp(FALSE_VAR);
	par0[6] = _bb_dbusearea(6, &par0[0]);
	_fgs_signal_a(6);
#line 6 "updAncona.prg"
	par0[0] = set_cv("kit_bc");
	par0[1] = _bb_dbsetindex(1, &par0[0]);
	_fgs_signal_a(8);
#line 8 "updAncona.prg"
	par0[0] = mk_var_cp(TRUE_VAR);
	par0[1] = cre_tmpvar();
	par0[2] = set_cv("ancona");
	par0[3] = set_cv("ANC");
lbor3:;
if(0) {
	par1[1] = TRUE_VAR;
	par1[0] = par1[1];
} else {
	par1[2] = fgsGetVar(_bbvar_nil);
	par1[0] = par1[2];
}
	par0[4] = par1[0];
	par0[5] = mk_var_cp(FALSE_VAR);
	par0[6] = _bb_dbusearea(6, &par0[0]);
	_fgs_signal_a(10);
#line 10 "updAncona.prg"
	par0[0] = (FSvar *)fgsPushIArea(_fsali_anc);
	par0[1] = _bb_dbgotop(0, 0);
	par0[2] = par0[1];
fgsPopArea((fgsWorkArea *) par0[0]);
	_fgs_signal_a(11);
#line 11 "updAncona.prg"
	while(1) {
	del_tmpvars(fn_stk.tmv_now);
		fn_stk.lin_no = 11;
	par0[0] = (FSvar *)fgsPushIArea(_fsali_anc);
	par0[1] = _bb_eof(0, 0);
	par0[2] = not_u(par0[1]);
	par0[3] = par0[2];
fgsPopArea((fgsWorkArea *) par0[0]);
		if(!if_u(par0[3])) break;
	_fgs_signal_a(13);
#line 13 "updAncona.prg"
	par0[0] = (FSvar *)fgsPushIArea(_fsali_kit);
	par0[1] = mk_var_cp(fgsGetIAliasField(_fsali_anc, _bbvar_id_kit));
	par0[2] = _bb_dbseek(1, &par0[1]);
	par0[3] = par0[2];
fgsPopArea((fgsWorkArea *) par0[0]);
if(!if_u(par0[3])) goto lbelse4;
	_fgs_signal_a(14);
#line 14 "updAncona.prg"
	par0[0] = n_lt_u(0.0, fgsGetIAliasField(_fsali_kit, _bbvar_idtipokit));
if(!if_u(par0[0])) goto lbelse5;
	_fgs_signal_a(15);
#line 15 "updAncona.prg"
	par0[0] = mk_var_cp(fgsGetIAliasField(_fsali_kit, _bbvar_idtipokit));
	par0[1] = _bb_gettkit(1, &par0[0]);
	par0[2] = cpy_var  (mv_names[_bbvar_tnome].v, par0[1]);
	_fgs_signal_a(15);
#line 15 "updAncona.prg"
	par0[0] = set_Iv((int) 4);
	par0[1] = set_Iv((int) 1);
	par0[2] = _bb_devpos(2, &par0[0]);
	_fgs_signal_a(16);
#line 16 "updAncona.prg"
	par2[0] = fgsGetIAliasField(_fsali_kit, _bbvar_idtipokit);
	par2[1] = _bb_str(1, &par2[0]);
	par1[0] = par2[1];
	par1[1] = _bb_ltrim(1, &par1[0]);
	par1[2] = c_add_u("TipoKit: ID=", par1[1]);
	par0[0] = par1[2];
	par0[1] = _bb_devout(1, &par0[0]);

	goto lbendif5;
lbelse5:;
	fn_stk.lin_no = 18;
	par0[0] = n_lt_u(0.0, fgsGetIAliasField(_fsali_kit, _bbvar_categoria));
if(if_u(par0[0])) {
	_fgs_signal_a(19);
#line 19 "updAncona.prg"
	par0[0] = mk_var_cp(fgsGetIAliasField(_fsali_kit, _bbvar_categoria));
	par0[1] = _bb_getkitv(1, &par0[0]);
	par0[2] = cpy_var  (mv_names[_bbvar_tnome].v, par0[1]);
	_fgs_signal_a(19);
#line 19 "updAncona.prg"
	par0[0] = set_Iv((int) 4);
	par0[1] = set_Iv((int) 1);
	par0[2] = _bb_devpos(2, &par0[0]);
	_fgs_signal_a(20);
#line 20 "updAncona.prg"
	par2[0] = fgsGetIAliasField(_fsali_kit, _bbvar_categoria);
	par2[1] = _bb_str(1, &par2[0]);
	par1[0] = par2[1];
	par1[1] = _bb_ltrim(1, &par1[0]);
	par1[2] = c_add_u("KitVar: Categoria=", par1[1]);
	par0[0] = par1[2];
	par0[1] = _bb_devout(1, &par0[0]);

	goto lbendif5;
}
	fn_stk.lin_no = 22;
	_fgs_signal_a(23);
#line 23 "updAncona.prg"
	par0[0] = set_cvar (mv_names[_bbvar_tnome].v, "");
	_fgs_signal_a(23);
#line 23 "updAncona.prg"
	par0[0] = set_Iv((int) 5);
	par0[1] = set_Iv((int) 1);
	par0[2] = _bb_devpos(2, &par0[0]);
	_fgs_signal_a(24);
#line 24 "updAncona.prg"
	par2[0] = fgsGetIAliasField(_fsali_kit, _bbvar_idtipokit);
	par2[1] = _bb_str(1, &par2[0]);
	par1[0] = par2[1];
	par1[1] = _bb_ltrim(1, &par1[0]);
	par1[2] = c_add_u("Errore:  TipoKit=	", par1[1]);
	par1[3] = u_add_c(par1[2], "  Categoria=", 0);
	par2[2] = fgsGetIAliasField(_fsali_kit, _bbvar_categoria);
	par2[3] = _bb_str(1, &par2[2]);
	par1[4] = par2[3];
	par1[5] = _bb_ltrim(1, &par1[4]);
	par1[6] = u_add_u(par1[3], par1[5], 0);
	par0[0] = par1[6];
	par0[1] = _bb_devout(1, &par0[0]);

	lbendif5:;
	_fgs_signal_a(28);
#line 28 "updAncona.prg"
	par0[0] = cpy_var  (fgsGetIAliasField(_fsali_anc, _bbvar_desc_kit), fgsGetVar(_bbvar_tnome));

	goto lbendif4;
lbelse4:;
	fn_stk.lin_no = 29;


	lbendif4:;
	_fgs_signal_a(31);
#line 31 "updAncona.prg"
	par0[0] = (FSvar *)fgsPushIArea(_fsali_anc);
	par0[1] = _bb_dbskip(0, 0);
	par0[2] = par0[1];
fgsPopArea((fgsWorkArea *) par0[0]);
	}

del_tmpvars(fn_stk.tmv_now);

	_fgs_signal_a(34);
#line 34 "updAncona.prg"
	par0[0] = (FSvar *)fgsPushIArea(_fsali_anc);
	par0[1] = _bb_dbcloseare(0, 0);
	par0[2] = par0[1];
fgsPopArea((fgsWorkArea *) par0[0]);
	_fgs_signal_a(36);
#line 36 "updAncona.prg"
	par0[0] = (FSvar *)fgsPushIArea(_fsali_kit);
	par0[1] = _bb_dbcloseare(0, 0);
	par0[2] = par0[1];
fgsPopArea((fgsWorkArea *) par0[0]);
	_fgs_signal_a(38);
#line 38 "updAncona.prg"
	par0[0] = (FSvar *)fgsPushIArea(_fsali_tkit);
	par0[1] = _bb_dbcloseare(0, 0);
	par0[2] = par0[1];
fgsPopArea((fgsWorkArea *) par0[0]);
	_fgs_signal_a(40);
#line 40 "updAncona.prg"
	par0[0] = (FSvar *)fgsPushIArea(_fsali_kitv);
	par0[1] = _bb_dbcloseare(0, 0);
	par0[2] = par0[1];
fgsPopArea((fgsWorkArea *) par0[0]);
	_fgs_signal_a(42);
#line 42 "updAncona.prg"
	par0[0] = _bb___quit(0, 0);
/* * ------------------------------ */
_adios:	return fgs_fn_end(&fn_stk, 'U');
}


FSvar * _bb_gettkit (parno, parptr)
FSint   parno;
FSvar * parptr[];
{
	fn_stack fn_stk;
	FSchar *__who_me=fgs_fn_start(&fn_stk,fgs_file_name,"gettkit",45,parno);

	FSvar *par0[4];
	FSvar *_fgspvar_pid;
	FSvar *_fgslvar_ret = fgs_local_param(0, 0, 0, 'U');
	if(fgsDonotenter) init_ref_data();
	_fgspvar_pid = fgs_local_param(parno, 0, parptr, 'U');
/* * ------------------------------ */
	_fgs_signal_a(47);
#line 47 "updAncona.prg"
	cpy_var(_fgslvar_ret, set_cv(""));
	_fgs_signal_a(48);
#line 48 "updAncona.prg"
	par0[0] = (FSvar *)fgsPushIArea(_fsali_tkit);
	par0[1] = mk_var_cp(_fgspvar_pid);
	par0[2] = _bb_dbseek(1, &par0[1]);
	par0[3] = par0[2];
fgsPopArea((fgsWorkArea *) par0[0]);
if(!if_u(par0[3])) goto lbelse6;
	_fgs_signal_a(49);
#line 49 "updAncona.prg"
	par0[0] = cpy_var  (_fgslvar_ret, fgsGetIAliasField(_fsali_tkit, _bbvar_nome));

	goto lbendif6;
lbelse6:;
	fn_stk.lin_no = 50;


	lbendif6:;
	_fgs_signal_a(51);
#line 51 "updAncona.prg"
	fn_stk.rvalptr = mk_var_cp(_fgslvar_ret);
	goto _adios;
lbreturn7:;
/* * ------------------------------ */
_adios:	return fgs_fn_end(&fn_stk, 'U');
}


FSvar * _bb_getkitv (parno, parptr)
FSint   parno;
FSvar * parptr[];
{
	fn_stack fn_stk;
	FSchar *__who_me=fgs_fn_start(&fn_stk,fgs_file_name,"getkitv",53,parno);

	FSvar *par0[4];
	FSvar *_fgspvar_pid;
	FSvar *_fgslvar_ret = fgs_local_param(0, 0, 0, 'U');
	if(fgsDonotenter) init_ref_data();
	_fgspvar_pid = fgs_local_param(parno, 0, parptr, 'U');
/* * ------------------------------ */
	_fgs_signal_a(55);
#line 55 "updAncona.prg"
	cpy_var(_fgslvar_ret, set_cv(""));
	_fgs_signal_a(56);
#line 56 "updAncona.prg"
	par0[0] = (FSvar *)fgsPushIArea(_fsali_kitv);
	par0[1] = mk_var_cp(_fgspvar_pid);
	par0[2] = _bb_dbseek(1, &par0[1]);
	par0[3] = par0[2];
fgsPopArea((fgsWorkArea *) par0[0]);
if(!if_u(par0[3])) goto lbelse8;
	_fgs_signal_a(57);
#line 57 "updAncona.prg"
	par0[0] = cpy_var  (_fgslvar_ret, fgsGetIAliasField(_fsali_kitv, _bbvar_nome));

	goto lbendif8;
lbelse8:;
	fn_stk.lin_no = 58;


	lbendif8:;
	_fgs_signal_a(59);
#line 59 "updAncona.prg"
	fn_stk.rvalptr = mk_var_cp(_fgslvar_ret);
	goto _adios;
lbreturn9:;
	_fgs_signal_a(60);
#line 60 "updAncona.prg"
_adios:	return fgs_fn_end(&fn_stk, 'U');
}



static struct f_ali_tab ali_tab[] = {
	{ "anc", &_fsali_anc },
	{ "kit", &_fsali_kit },
	{ "tkit", &_fsali_tkit },
	{ "kitv", &_fsali_kitv },
	{ "", 0 }
};

static struct g_ali_tab g_ali_tab = { "FSfntab	", ali_tab };

static FSvoid refer_to_alias()
{ 
    struct g_ali_tab *ss = &g_ali_tab;
    ss = &ss[1];
}


static f_sym_tab loc_tab[] = {
	{ "updancona", _bb_updancona },
	{ "gettkit", _bb_gettkit },
	{ "getkitv", _bb_getkitv },
	{ "", 0 }
};

static g_sym_tab g_loc_tab = { "FSfntab", loc_tab };

static FSvoid refer_to_func()
{ 
    g_sym_tab *ss = &g_loc_tab;
    ss = &ss[1];
}


static struct f_var_tab var_tab[] = {
	{ "id_kit", &_bbvar_id_kit },
	{ "idtipokit", &_bbvar_idtipokit },
	{ "categoria", &_bbvar_categoria },
	{ "desc_kit", &_bbvar_desc_kit },
	{ "nil", &_bbvar_nil },
	{ "tnome", &_bbvar_tnome },
	{ "nome", &_bbvar_nome },
	{ "", &fgs_fs440 }
};

static struct g_var_tab g_var_tab = { "FSfntab", var_tab };

static FSvoid refer_to()
{ 
    struct g_var_tab *vv = &g_var_tab;
    vv = &vv[1];
}

static FSvoid init_ref_data()
{ 
	refer_to_alias();
	refer_to_func();
	refer_to();
}

