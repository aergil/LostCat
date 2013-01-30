function Bubble(opt_options) {
  this.extend(Bubble, google.maps.OverlayView);
  this.baseZIndex_ = 100;
  this.isOpen_ = false;

  var options = opt_options || {};
  
  this.buildDom_(options.content, options.statut);
  this.setValues(options);
}
window['Bubble'] = Bubble;

Bubble.prototype.extend = function(obj1, obj2) {
  return (function(object) {
    for (var property in object.prototype) {
      this.prototype[property] = object.prototype[property];
    }
    return this;
  }).apply(obj1, [obj2]);
};


Bubble.prototype.buildDom_ = function(content, statut) {
  var bubble = this.bubble_ = document.createElement('DIV');
  if(statut == "PERDU"){
	  bubble.className = 'bubble btn btn-danger';
  } else{
	  bubble.className = 'bubble btn btn-success';
  }
	  

  var close = this.close_ = document.createElement('IMG');
  close.className = 'bubbleClose';
  close.src = '/assets/images/close.png';

  var that = this;
  google.maps.event.addDomListener(close, 'click', function() {
    that.close();
    google.maps.event.trigger(that, 'closeclick');
  });

  var contentContainer = this.contentContainer_ = document.createElement('DIV');
  contentContainer.className = 'bubbleContentContainer';
  contentContainer.innerHTML = content;

  var content = this.content_ = document.createElement('DIV');
  contentContainer.appendChild(content);

  var arrow = this.arrow_ = document.createElement('DIV');
  contentContainer.className = 'bubbleArrow';

  bubble.style['display'] = 'inline';

 // bubble.appendChild(close);
  bubble.appendChild(contentContainer);
  bubble.appendChild(arrow);
};


Bubble.prototype.setZIndex = function(zIndex) {  this.set('zIndex', zIndex);};
Bubble.prototype['setZIndex'] = Bubble.prototype.setZIndex;
Bubble.prototype.getZIndex = function() {  return parseInt(this.get('zIndex'), 10) || this.baseZIndex_;};
Bubble.prototype.zIndex_changed = function() {
  var zIndex = this.getZIndex();
  this.bubble_.style['zIndex'] = this.baseZIndex_ = zIndex;
  this.close_.style['zIndex'] = zIndex + 1;
};
Bubble.prototype['zIndex_changed'] = Bubble.prototype.zIndex_changed;



Bubble.prototype.px = function(num) {
  if (num) {
    // 0 doesn't need to be wrapped
    return num + 'px';
  }
  return num;
};


Bubble.prototype.addEvents_ = function() {
  // We want to cancel all the events so they do not go to the map
  var events = ['mousedown', 'mousemove', 'mouseover', 'mouseout', 'mouseup', 'mousewheel', 'DOMMouseScroll', 'touchstart', 'touchend', 'touchmove', 'dblclick', 'contextmenu', 'click'];

  var bubble = this.bubble_;
  this.listeners_ = [];
  for (var i = 0, event; event = events[i]; i++) {
    this.listeners_.push(
      google.maps.event.addDomListener(bubble, event, function(e) {
        e.cancelBubble = true;
        if (e.stopPropagation) { e.stopPropagation(); }
      })
    );
  }
};


/**
 * On Adding the Bubble to a map
 * Implementing the OverlayView interface
 */
Bubble.prototype.onAdd = function() {
  if (!this.bubble_) { this.buildDom_();  }

  this.addEvents_();

  var panes = this.getPanes();
  if (panes) {
    panes.floatPane.appendChild(this.bubble_);
  }
};
Bubble.prototype['onAdd'] = Bubble.prototype.onAdd;


/**
 * Draw the Bubble
 * Implementing the OverlayView interface
 */
Bubble.prototype.draw = function() {
  var projection = this.getProjection();

  if (!projection) { return;  }

  var latLng = (this.get('position'));

  if (!latLng) {
    this.close();
    return;
  }

  var pos = projection.fromLatLngToDivPixel(latLng);
  var width = 52; //  width bubble
  var height = 60; // height bubble + Arrow

  if (!width) { return; }

  // Adjust for the height of the info bubble
  var top = pos.y - height ;
  var left = pos.x - (width / 2);

  this.bubble_.style['top'] = this.px(top);
  this.bubble_.style['left'] = this.px(left);
};
Bubble.prototype['draw'] = Bubble.prototype.draw;

Bubble.prototype.isOpen = function() {return this.isOpen_;};
Bubble.prototype['isOpen'] = Bubble.prototype.isOpen;

Bubble.prototype.close = function() {
  if (this.bubble_) {
    this.bubble_.style['display'] = 'none';
  }
  this.isOpen_ = false;
};
Bubble.prototype['close'] = Bubble.prototype.close;


/**
 * Open the Bubble (asynchronous).
 *
 * @param {google.maps.Map=} opt_map Optional map to open on.
 * @param {google.maps.MVCObject=} opt_anchor Optional anchor to position at.
 */
Bubble.prototype.open = function(opt_map, opt_anchor) {
  var that = this;
  window.setTimeout(function() {    that.open_(opt_map, opt_anchor);  }, 0);
};

/**
 * Open the Bubble
 * @private
 * @param {google.maps.Map=} opt_map Optional map to open on.
 * @param {google.maps.MVCObject=} opt_anchor Optional anchor to position at.
 */
Bubble.prototype.open_ = function(opt_map, opt_anchor) {
  //this.updateContent_();
  if (opt_map) {
    this.setMap(opt_map);
  }

  if (opt_anchor) {
    this.set('anchor', opt_anchor);
    this.bindTo('anchorPoint', opt_anchor);
    this.bindTo('position', opt_anchor);
  }

  // Show the bubble and the show
  this.bubble_.style['display'] = '';

  this.redraw_();
  this.isOpen_ = true;
};
Bubble.prototype['open'] = Bubble.prototype.open;


/**
 * Set the position of the Bubble
 *
 * @param {google.maps.LatLng} position The position to set.
 */
Bubble.prototype.setPosition = function(position) {
  if (position) {
    this.set('position', position);
  }
};
Bubble.prototype['setPosition'] = Bubble.prototype.setPosition;


Bubble.prototype.getPosition = function() {  return (this.get('position'));};
Bubble.prototype['getPosition'] = Bubble.prototype.getPosition;

Bubble.prototype.position_changed = function() { this.draw();};
Bubble.prototype['position_changed'] = Bubble.prototype.position_changed;


/**
 * Redraw the Bubble
 * @private
 */
Bubble.prototype.redraw_ = function() {
  //this.figureOutSize_();
  //this.positionCloseButton_();
  this.draw();
};
