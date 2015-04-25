# read season stats and teams
gamestats <- read.csv(file="regular_season_detailed_results_2015.csv")
teams <- read.csv(file="teams.csv")
tournament <- read.csv(file="tourney_seeds_2015.csv")


# add stats to the teams table
teams["wins"] <- NA
teams["losses"] <- NA
teams["Wins/Losses"] <- NA
teams["2pts_Made/Attempt"] <- NA
teams["3pts_Made/Attempt"] <- NA
teams["FT_Made/Attempt"] <- NA
teams["Rebounds"] <- NA
teams["Assists"] <- NA
teams["Turnovers"] <- NA
teams["Steals"] <- NA
teams["Blocks"] <- NA
teams["PersonalFoul"] <- NA

# loop thru team list
for(i in 1:length(teams$team_id)){
  # go thru team wins in every game
  # get the numbers for each game stats
  team_wins = 0
  team_losses = 0
  wfgm = 0
  wfga = 0
  wfgm3 = 0
  wfga3 = 0
  wftm = 0
  wfta = 0
  rebounds = 0
  wast = 0
  wto = 0
  wstl = 0
  wblk = 0
  wpf = 0
  # with one team loop to find the wins
  for(j in 1:length(gamestats$wteam)){
    # count how many times you see this team wins
    if(teams$team_id[i] == gamestats$wteam[j]){
      team_wins = team_wins + 1
      wfgm = wfgm + gamestats$wfgm[j]
      wfga = wfga + gamestats$wfga[j]
      wfgm3 = wfgm3 + gamestats$wfgm3[j]
      wfga3 = wfga3 + gamestats$wfga3[j]
      wftm = wftm + gamestats$wftm[j]
      wfta = wfta + gamestats$wfta[j]
      rebounds = rebounds + gamestats$wor[j] + gamestats$wdr[j]
      wast = wast + gamestats$wast[j]
      wto = wto + gamestats$wto[j]
      wstl = wstl + gamestats$wstl[j]
      wblk = wblk + gamestats$wblk[j]
      wpf = wpf + gamestats$wpf[j]
    }
    # with one team loop to find losses
    if(teams$team_id[i] == gamestats$lteam[j]){
      team_losses = team_losses + 1
      wfgm = wfgm + gamestats$wfgm[j]
      wfga = wfga + gamestats$wfga[j]
      wfgm3 = wfgm3 + gamestats$wfgm3[j]
      wfga3 = wfga3 + gamestats$wfga3[j]
      wftm = wftm + gamestats$wftm[j]
      wfta = wfta + gamestats$wfta[j]
      rebounds = gamestats$wor[j] + gamestats$wdr[j]
      wast = wast + gamestats$wast[j]
      wto = wto + gamestats$wto[j]
      wstl = wstl + gamestats$wstl[j]
      wblk = wblk + gamestats$wblk[j]
      wpf = wpf + gamestats$wpf[j]
    }
    #add to teams table wins column
    w_l_ratio <- team_wins / (team_wins + team_losses)
    teams$wins[i] <- team_wins
    teams$losses[i] <- team_losses
    teams$"Wins/Losses"[i] <- w_l_ratio
    
    #number of games
    number_games = (team_wins + team_losses)
    
    # get ratios
    wfgm_wfga_ratio = wfgm / wfga
    wfgm3_wfga3_ratio = wfgm3 / wfga3
    wftm_wfta_ratio = wftm / wfta
    
    teams$"2pts_Made/Attempt"[i] = wfgm_wfga_ratio
    teams$"3pts_Made/Attempt"[i] = wfgm3_wfga3_ratio
    teams$"FT_Made/Attempt"[i] = wftm_wfta_ratio
    teams$Rebounds[i] = rebounds
    teams$Assists[i] = wast
    teams$Turnovers[i] = wto
    teams$Steals[i] = wstl
    teams$Blocks[i] = wblk
    teams$PersonalFoul[i] = wpf
  }
}

#tournament
for(1 in k:length(tournament$team)){
 #tournament$team[k]
 for(1 in m:length(teams$team_id)){
   if(tournament$team[k] == teams$team_id[m]){
     
   }
 }
}

